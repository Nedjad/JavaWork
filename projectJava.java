  import java.util.PriorityQueue;
  import java.util.Random;
  import java.io.FileWriter;
  import java.io.IOException;

  public class Main {

      public static void main(String[] args) {
          // Создание PriorityQueue для хранения игрушек
          PriorityQueue<Toy> toyQueue = new PriorityQueue<>();

          // Добавление игрушек в очередь
          toyQueue.add(new Toy(1, "робот", 2));
          toyQueue.add(new Toy(2, "конструктор", 2));
          toyQueue.add(new Toy(3, "кукла", 6));

          // Вызов метода Get 10 раз и запись результатов в файл
          try {
              FileWriter writer = new FileWriter("output.txt");
              Random random = new Random();
              for (int i = 0; i < 10; i++) {
                  int randomNumber = random.nextInt(10);
                  Toy randomToy = getRandomToy(toyQueue, randomNumber);
                  writer.write(randomToy.getId() + "\n");
              }
              writer.close();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

      // Метод для получения случайной игрушки в соответствии с весом
      private static Toy getRandomToy(PriorityQueue<Toy> toyQueue, int randomNumber) {
          int totalWeight = toyQueue.stream().mapToInt(Toy::getWeight).sum();
          int randomWeight = randomNumber % totalWeight;
          int sum = 0;
          for (Toy toy : toyQueue) {
              sum += toy.getWeight();
              if (randomWeight < sum) {
                  return toy;
              }
          }
          return toyQueue.peek();
      }

      // Класс игрушки
      static class Toy implements Comparable<Toy> {
          private int id;
          private String name;
          private int weight;

          public Toy(int id, String name, int weight) {
              this.id = id;
              this.name = name;
              this.weight = weight;
          }

          public int getId() {
              return id;
          }

          public String getName() {
              return name;
          }

          public int getWeight() {
              return weight;
          }

          @Override
          public int compareTo(Toy other) {
              return Integer.compare(this.weight, other.weight);
          }
      }
  }