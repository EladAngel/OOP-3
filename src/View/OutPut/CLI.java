package View.OutPut;

public class CLI implements View{
    private final MessageCallBack mc;
    public CLI(){
        mc= this::display;
    }
    private void display(String message){
        System.out.println(message);
    }
    public MessageCallBack getMessageCallBack() {
        return mc;
    }
}
