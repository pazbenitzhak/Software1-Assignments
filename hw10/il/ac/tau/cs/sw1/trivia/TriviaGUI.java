package il.ac.tau.cs.sw1.trivia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class TriviaGUI {

	private static final int MAX_ERRORS = 3;
	private Shell shell;
	private Label scoreLabel;
	private Composite questionPanel;
	private Label startupMessageLabel;
	private Font boldFont;
	private String lastAnswer;
	private int consecutiveFailures;
	private int score;
	private int is5050Once;
	private int isPassOnce;
	private HashMap<String, String> aqs;
	private String currentQuestion;
	private List<String> currentAns;
	private String answersString;
	private String rightChoice;
	private int totalQuesNum;
	
	// Currently visible UI elements.
	Label instructionLabel;
	Label questionLabel;
	private List<Button> answerButtons = new LinkedList<>();
	private Button passButton;
	private Button fiftyFiftyButton;

	public void open() {
		createShell();
		runApplication();
	}

	/**
	 * Creates the widgets of the application main window
	 */
	private void createShell() {
		Display display = Display.getDefault();
		shell = new Shell(display);
		shell.setText("Trivia");

		// window style
		Rectangle monitor_bounds = shell.getMonitor().getBounds();
		shell.setSize(new Point(monitor_bounds.width / 3,
				monitor_bounds.height / 4));
		shell.setLayout(new GridLayout());

		FontData fontData = new FontData();
		fontData.setStyle(SWT.BOLD);
		boldFont = new Font(shell.getDisplay(), fontData);

		// create window panels
		createFileLoadingPanel();
		createScorePanel();
		createQuestionPanel();
	}

	/**
	 * Creates the widgets of the form for trivia file selection
	 */
	private void createFileLoadingPanel() {
		final Composite fileSelection = new Composite(shell, SWT.NULL);
		fileSelection.setLayoutData(GUIUtils.createFillGridData(1));
		fileSelection.setLayout(new GridLayout(4, false));

		final Label label = new Label(fileSelection, SWT.NONE);
		label.setText("Enter trivia file path: ");

		// text field to enter the file path
		final Text filePathField = new Text(fileSelection, SWT.SINGLE
				| SWT.BORDER);
		filePathField.setLayoutData(GUIUtils.createFillGridData(1));

		// "Browse" button
		final Button browseButton = new Button(fileSelection, SWT.PUSH);
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter() {
			Text text = filePathField;
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() instanceof Button) {
					String str = GUIUtils.getFilePathFromFileDialog(shell);
					if (str==null) {
						return;
					}
					this.text.setText(str);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				return;
			}
			
		});

		// "Play!" button
		final Button playButton = new Button(fileSelection, SWT.PUSH);
		playButton.setText("Play!");
		playButton.addSelectionListener(new PlayListener(filePathField));
	}

	/**
	 * Creates the panel that displays the current score
	 */
	private void createScorePanel() {
		Composite scorePanel = new Composite(shell, SWT.BORDER);
		scorePanel.setLayoutData(GUIUtils.createFillGridData(1));
		scorePanel.setLayout(new GridLayout(2, false));

		final Label label = new Label(scorePanel, SWT.NONE);
		label.setText("Total score: ");

		// The label which displays the score; initially empty
		scoreLabel = new Label(scorePanel, SWT.NONE);
		scoreLabel.setLayoutData(GUIUtils.createFillGridData(1));
	}

	/**
	 * Creates the panel that displays the questions, as soon as the game
	 * starts. See the updateQuestionPanel for creating the question and answer
	 * buttons
	 */
	private void createQuestionPanel() {
		questionPanel = new Composite(shell, SWT.BORDER);
		questionPanel.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		questionPanel.setLayout(new GridLayout(2, true));

		// Initially, only displays a message
		startupMessageLabel = new Label(questionPanel, SWT.NONE);
		startupMessageLabel.setText("No question to display, yet.");
		startupMessageLabel.setLayoutData(GUIUtils.createFillGridData(2));
	}

	/**
	 * Serves to display the question and answer buttons
	 */
	private void updateQuestionPanel(String question, List<String> answers) {
		// Save current list of answers.
		List<String> currentAnswers = answers;
		
		// clear the question panel
		Control[] children = questionPanel.getChildren();
		for (Control control : children) {
			control.dispose();
		}

		// create the instruction label
		instructionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		instructionLabel.setText(lastAnswer + "Answer the following question:");
		instructionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the question label
		questionLabel = new Label(questionPanel, SWT.CENTER | SWT.WRAP);
		questionLabel.setText(question);
		questionLabel.setFont(boldFont);
		questionLabel.setLayoutData(GUIUtils.createFillGridData(2));

		// create the answer buttons
		answerButtons.clear();
		for (int i = 0; i < 4; i++) {
			Button answerButton = new Button(questionPanel, SWT.PUSH | SWT.WRAP);
			answerButton.setText(answers.get(i));
			GridData answerLayoutData = GUIUtils.createFillGridData(1);
			answerLayoutData.verticalAlignment = SWT.FILL;
			answerButton.setLayoutData(answerLayoutData);
			if (consecutiveFailures==3) {
				continue;
			}
			answerButton.addSelectionListener(new answerListener(answers.get(i)));
			
			answerButtons.add(answerButton);
		}

		// create the "Pass" button to skip a question
		passButton = new Button(questionPanel, SWT.PUSH);
		passButton.setText("Pass");
		GridData data = new GridData(GridData.END, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		passButton.setLayoutData(data);
		if (score<=0&&isPassOnce!=0) {
			passButton.setEnabled(false);
		}
		passButton.addSelectionListener(new PassListener(passButton));
		
		// create the "50-50" button to show fewer answer options
		fiftyFiftyButton = new Button(questionPanel, SWT.PUSH);
		fiftyFiftyButton.setText("50-50");
		data = new GridData(GridData.BEGINNING, GridData.CENTER, true,
				false);
		data.horizontalSpan = 1;
		fiftyFiftyButton.setLayoutData(data);
		if (score<=0&&is5050Once!=0) {
			fiftyFiftyButton.setEnabled(false);
		}
		fiftyFiftyButton.addSelectionListener(new FiftyListener());

		// two operations to make the new widgets display properly
		questionPanel.pack();
		questionPanel.getParent().layout();
	}

	/**
	 * Opens the main window and executes the event loop of the application
	 */
	private void runApplication() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
		boldFont.dispose();
	}
	
	public static HashMap<String, String> readQuestions(String filepath) throws FileNotFoundException, IOException {
		HashMap<String, String> answersQuestions = new HashMap<String, String>();
		File fromFile = new File(filepath);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile));
		String line;
		while ((line=bufferedReader.readLine())!=null) {
			String[] qaArray = line.split("\\?");
			answersQuestions.put(qaArray[1], qaArray[0]+"?");
		}
		bufferedReader.close();
		return answersQuestions;
	}
	
	public void initiateQuestion() {
		Set<String> answersSet = aqs.keySet();
		List<String> answersList = new ArrayList<String>(answersSet);
		Random random = new Random();
		int index = random.nextInt(answersList.size());
		String answers = answersList.get(index);
		answersString = answers;
		String[] splitArray = answers.split("\\t");
		String[] answersArray = new String[4];
		System.arraycopy(splitArray, 1, answersArray, 0, 4);
		List<String> splitAnswers = Arrays.asList(answersArray);
		rightChoice = splitAnswers.get(0);
		Collections.shuffle(splitAnswers);
		currentAns = splitAnswers;
		currentQuestion = aqs.get(answers);
		updateQuestionPanel(currentQuestion, currentAns);
	}
	
	public class PlayListener implements SelectionListener {
		Text text;
		HashMap<String, String> QA;
		
		public PlayListener(Text t) {
			this.text = t;
		}
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.getSource() instanceof Button) {
				try {
					score = 0;
					scoreLabel.setText(String.valueOf(score));
					lastAnswer="";
					this.QA = readQuestions(this.text.getText());
					aqs = this.QA;
					totalQuesNum = aqs.size();
					initiateQuestion();
					
				}
				catch (Exception exc) {
					System.out.println("Exception! " + exc.getClass().getSimpleName()+exc);
					return;
				}
			}
		}
		public void widgetDefaultSelected(SelectionEvent e) {
			return;
		}
	}
	
	public class answerListener implements SelectionListener {
		String answerText;
		
		public answerListener(String str) {
			this.answerText = str;
		}
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.getSource() instanceof Button) {
				if (this.answerText.equals(rightChoice)) {
					score+=3;
					scoreLabel.setText(String.valueOf(score));
					consecutiveFailures=0;
					lastAnswer = "Correct! ";
					aqs.remove(answersString);
					if (aqs.size()==0) {
						GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " +score +" after "+totalQuesNum +" questions.");
					}
					initiateQuestion();
				}
				else {
					score-=2;
					scoreLabel.setText(String.valueOf(score));
					consecutiveFailures++;
					lastAnswer = "Wrong... ";
					aqs.remove(answersString);
					if (aqs.size()==0) {
						GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " +score +" after "+totalQuesNum +" questions.");
					}
					else if (consecutiveFailures==MAX_ERRORS) {
						GUIUtils.showInfoDialog(shell, "GAME OVER", "Your final score is " +score +" after "+(totalQuesNum-aqs.size()) +" questions.");
					}
					initiateQuestion();
				}
				
				
			}
		}
		public void widgetDefaultSelected(SelectionEvent e) {
			return;
		}
	}
	
	public class PassListener implements SelectionListener {
		
		private Button field;
		
		public PassListener(Button button) {
			this.field = button;
		}
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (e.getSource() instanceof Button) {
				if (isPassOnce==0) {
					isPassOnce++;
					lastAnswer="";
					initiateQuestion();
				}
				else {
					if (score<=0) {
						this.field.setEnabled(false);
					}
					else {
						score--;
						scoreLabel.setText(String.valueOf(score));
						lastAnswer="";
						initiateQuestion();
					}
				}
			}
		}
		
		public void widgetDefaultSelected(SelectionEvent e) {
			return;
		}
	}
	
	public void FiftyHandler() {
		String[] possibleMatches = new String[3];
		int k = 0;
		for (int i=0;i<currentAns.size();i++) {
			if (!currentAns.get(i).equals(rightChoice)) {
				possibleMatches[k]=currentAns.get(i);
				k++;
				}
		}
		Random random = new Random();
		int a = random.nextInt(2);
		int b = a;
		while (b==a) {
			b = random.nextInt(2);
		}
		for (Button button: answerButtons) {
			if (button.getText()==possibleMatches[a]||button.getText()==possibleMatches[b]) {
				button.setEnabled(false);
			}
		}
	}
		
	public class FiftyListener implements SelectionListener {
			
			public FiftyListener() {
				
			}
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() instanceof Button) {
					if (is5050Once==0) {
						is5050Once++;
						FiftyHandler();
					}
					else {
						if (score>0) {
							score--;
							scoreLabel.setText(String.valueOf(score));
							FiftyHandler();
						}
					}
				}
				}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				return;
			}
		}
}
