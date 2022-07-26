import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    String[] products = {"Яблоко", "Помидор", "Апельсин", "Груша"};
    int[] prices = {30, 50, 70, 40};
    int sumProducts = 0;
    int discount = 0;
    String saleinformation = "";

    Scanner scanner = new Scanner(System.in);
    int[] amountOfProductsInBasket = new int[products.length];

    System.out.println("Список возможных товаров для покупки");
    for (int i = 0; i < products.length; i++) {
      String product = products[i];
      int price = prices[i];
      int position = i + 1;
      System.out.println(position + ". " + product + " " + price + " руб/шт");
    }

    while (true) {
      int productNumber;
      int productCount;
      try {
        System.out.println("Выберите товар и количество или введите `end`");
        String inputString = scanner.nextLine();
        if (inputString.equals("end")) {
          break;
        }
        String[] input = inputString.split(" ");
        if (input.length != 2) {
          System.out.println("Ошибка ввода! Должно быть введено 2 числа!");
          continue;
        }
        productNumber = Integer.parseInt(input[0]);
        if (productNumber > products.length || productNumber <= 0) {
          System.out.println(
              "Ошибка! Товара с таким номером не существует! Внимательно ознакомьтесь со списком продуктов.");
          continue;
        }
        productCount = Integer.parseInt(input[1]);
        if (productCount < 0 && (productCount * -1) > amountOfProductsInBasket[productNumber - 1]) {
          System.out.println("Ошибка! Количество товара для покупки должно быть больше 0.");
          continue;
        }
        if (productCount == 0) {
          amountOfProductsInBasket[productNumber - 1] = 0;
          continue;
        }
        amountOfProductsInBasket[productNumber - 1] += productCount;
      } catch (NumberFormatException exception) {
        System.out.println("Введенные данные не являются числом!");
      }
    }

    System.out.println("Ваша корзина:");
    for (int i = 0; i < products.length; i++) {
      if (amountOfProductsInBasket[i] != 0) {
        sumProducts = sumProducts + (prices[i] * amountOfProductsInBasket[i]);
        if (amountOfProductsInBasket[i] >= 3) {
          discount = prices[i] * (amountOfProductsInBasket[i] / 3);
          saleinformation = "Акция 3 по цене 2! ";
          sumProducts -= discount;
        }
        System.out.println(
            products[i]
                + " "
                + amountOfProductsInBasket[i]
                + " шт. "
                + prices[i]
                + " руб/шт. "
                + saleinformation
                + (amountOfProductsInBasket[i] * prices[i] - discount)
                + " рублей в сумме");
      }
    }
    System.out.println("Итого: " + sumProducts + " рублей");
  }
}
