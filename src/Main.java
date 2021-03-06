import adapter.PeekIterator;
import adapter.Peekerator;
import composite.FinancialInstrument;
import composite.Portfolio;
import composite.PricedComponent;
import singleton.FileLogger;
import singleton.Service;
import strategy.Panel;
import strategy.RedBackground;
import strategy.YellowBackground;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        test_covariance();
        test_composite();
        test_strategy();
        test_singleton();
        test_adapter();
    }

    private static void test_adapter() {
        List<Integer> myList = new ArrayList<>() {{
            add(1);
            add(2);
            add(3);
        }};

        ListIterator<Integer> iterator = myList.listIterator();
        Peekerator<Integer> peekerator = new PeekIterator<Integer>(iterator);

        System.out.println(peekerator.peek()); // 1
        System.out.println(iterator.next()); // 1
        System.out.println(peekerator.peek()); // 2

    }

    private static void test_covariance(){
        System.out.println(Object[].class.isAssignableFrom(String[].class));
        System.out.println(new String[0] instanceof Object[]);
        System.out.println(String[].class.getSuperclass());
    }

    private static void test_singleton(){
        Service myService = new Service();
        FileLogger l1 = myService.do_something_and_log();
        FileLogger l2 = myService.do_something_else_and_log();
        System.out.println(l1.equals(l2));
    }

    private static void test_composite(){
        PricedComponent myInstrument = new FinancialInstrument();
        System.out.println(myInstrument.getPrice());

        Portfolio myPortfolio = new Portfolio(new ArrayList<PricedComponent>());

        for (int i=0; i < 10; i++) {
            myPortfolio.addInstrument(new FinancialInstrument());
        }

        System.out.println(myPortfolio.getPrice());
    }

    private static void test_strategy(){
        Panel myPanel = new Panel();

        myPanel.setDrawingStrategy(new RedBackground());
        myPanel.drawBackground();

        myPanel.setDrawingStrategy(new YellowBackground());
        myPanel.drawBackground();
    }


}
