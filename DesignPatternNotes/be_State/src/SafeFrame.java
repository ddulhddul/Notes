import java.awt.*;
import java.awt.event.*;

public class SafeFrame extends Frame implements ActionListener, Context{
	private TextField textClock=new TextField(60); // 현재시간 표시
	private TextArea textScreen=new TextArea(10, 60); // 경비센터 출력
	private Button buttonUse=new Button("금고사용"); // 금고사용 버튼
	private Button buttonAlarm=new Button("비상벨"); // 비상벨 버튼
	private Button buttonPhone=new Button("일반통화"); // 일반통화 버튼
	private Button buttonExit=new Button("종료"); // 종료 버튼

	private State state=DayState.getInstance(); // 현재의 상태

	public SafeFrame(String title){
		super(title);
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());

		add(textClock, BorderLayout.NORTH);
		textClock.setEditable(false);

		add(textScreen, BorderLayout.CENTER);
		textScreen.setEditable(false);

		Panel panel=new Panel();
		panel.add(buttonUse);
		panel.add(buttonAlarm);
		panel.add(buttonPhone);
		panel.add(buttonExit);
		add(panel, BorderLayout.SOUTH);
		pack();
		setVisible(true);

		buttonUse.addActionListener(this);
		buttonAlarm.addActionListener(this);
		buttonPhone.addActionListener(this);
		buttonExit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		System.out.println(e.toString());
		if(e.getSource()==buttonUse){ // 금고사용 버튼
			state.doUse(this);
		}else if(e.getSource()==buttonAlarm){ // 비상벨 버튼
			state.doAlarm(this);
		}else if(e.getSource()==buttonPhone){ // 일반통화 버튼
			state.doPhone(this);
		}else if(e.getSource()==buttonExit){ // 종료 버튼
			System.exit(0);
		}else{
			System.out.println("?");
		}
	}
	public void setClock(int hour){
		String clockstring="현재 시간은 ";
		if(hour<10){
			clockstring+="0"+hour+":00";
		}else{
			clockstring+=hour+":00";
		}
		System.out.println(clockstring);
		textClock.setText(clockstring);
		state.doClock(this, hour);
	}
	public void changeState(State state){
		System.out.println(this.state+"에서"+state+"로 상태가 변화했습니다.");
		this.state=state;
	}
	public void callSecurityCenter(String msg){
		textScreen.append("call! "+msg+"\n");
	}
	public void recordLog(String msg){
		textScreen.append("record ... "+msg+"\n");
	}
}