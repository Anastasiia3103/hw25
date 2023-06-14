package hw25;

public class Lesson {
        private int id;
        private String name;
        private Homework homework;

        public Lesson(int id, String name, Homework homework) {
            this.id = id;
            this.name = name;
            this.homework = homework;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Homework getHomework() {
            return homework;
        }

        public void setHomework(Homework homework) {
            this.homework = homework;
        }
}

