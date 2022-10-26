import java.util.Scanner;

//В целом с точки зрения Code Style, описанном в курсе, не вижу ни одной ошибки.
//С названием переменных тоже все отлично (лучше чем у меня)
//Есть лиш одно замечание от меня. Описал его ниже.
public class DepositCalculator {
    double calculateComplexPercent(double amount, double yearRate, int depositPeriod) {
        final int MONTH_COUNT = 12;
        int places = 2;

        double pay = amount * Math.pow((1 + yearRate / MONTH_COUNT), MONTH_COUNT * depositPeriod);

        return roundValueByPlaces(pay, places);
    }

    double calculateSimplePercent(double amount, double yearRate, int depositPeriod) {
        int places = 2;

        return roundValueByPlaces(amount + amount * yearRate * depositPeriod, places);
    }

    double roundValueByPlaces(double value, int places) {
        double scale = Math.pow(10, places);

        return Math.round(value * scale) / scale;
    }

    void calculate() {
        //В курсе об этом ничего не было сказано, но вообще считается что переменная должна объявляться
        //там где используется.
        //Если переменные amount, period и action объявить вместе с присвоением значения,
        //то код станет и короче и читабельней.
        //Отделать outAmount и yearRate от if целыми блоками кода может быть даже опасно, т.к. человек сразу не видит
        //какое было значение изначально.
        //Хотя создание самой переменной yearRate правильный ход. В задании просто об этом ничего не было.
        int amount;
        int period;
        int action;
        double outAmount = 0;
        double yearRate = 0.06;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму вклада в рублях:");
        amount = scanner.nextInt();

        System.out.println("Введите срок вклада в годах:");
        period = scanner.nextInt();

        System.out.println("Выберите тип вклада, 1 - вклад с обычным процентом, 2 - вклад с капитализацией:");
        action = scanner.nextInt();

        if (action == 1) {
            outAmount = calculateComplexPercent(amount, yearRate, period);
        } else if (action == 2) {
            outAmount = calculateSimplePercent(amount, yearRate, period);
        }

        System.out.println("Результат вклада: " + amount + " за " + period + " лет превратятся в " + outAmount);
    }

    public static void main(String[] args) {
        new DepositCalculator().calculate();
    }
}
