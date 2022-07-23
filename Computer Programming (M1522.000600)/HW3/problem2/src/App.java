public class App {
    private static UserInterface ui;
    private static FrontEnd frontEnd;
    private static BackEnd backend;
    public static void main(String[] args) {
        onCreate();
    }
    private static void onCreate(){
        ui = new UserInterface();
        backend = new BackEnd();
        frontEnd = new FrontEnd(ui,backend);
        ui.createUI(frontEnd);
        ui.run();
    }
}
