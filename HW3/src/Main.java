import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        SingleLinkList<Football> contactList = new SingleLinkList<>();

        contactList.addToEnd(new Football("Inter Milan", "Italy"));
        contactList.addToEnd(new Football("Liverpool", "England"));
        contactList.addToEnd(new Football("Manchester City", "England"));
        contactList.addToEnd(new Football("Real Madrid", "Spain"));
        contactList.addToEnd(new Football("PSG", "France"));
        contactList.addToEnd(new Football("Bayern Munich", "Germany"));
        contactList.addToEnd(new Football("Zenit", "Russia"));

        for (Object contact : contactList) {
            System.out.println(contact);
        }
        contactList.reverse();

        System.out.println("_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_-#-_");

        for (Object contact : contactList) {
            System.out.println(contact);
        }
    }

    static class Football {
        String name;
        String country;

        public Football(String name, String country) {
            this.name = name;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Football Club: " + name + " - " + country;
        }
    }

    /**
     * Класс списка
     *
     * @param <T>
     */
    public static class SingleLinkList<T> implements Iterable {
        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        /**
         * Класс отдельного элемента
         *
         * @param <T>
         */
        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        // Проверка на то, пустая ли голова
        public boolean isEmpty() {
            return head == null;
        }

        // Заполнение списка
        public void addToEnd(T item) {

            // Выделение памяти для списка
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;

            // Если голова и хвост пустая, то присваеваем newItem
            if (isEmpty()) {
                head = newItem;
                tail = newItem;

                // Если не пустая, то передаём элементу адрес и ставим его в хвост
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        // Метод разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) { // Если не пусто и голова не равна нулю
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}