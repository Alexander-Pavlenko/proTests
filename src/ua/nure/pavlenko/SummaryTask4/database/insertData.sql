set @@character_set_client='utf8';
INSERT INTO user(`first_name`, `last_name`, `login`, `password`, `role`, `e_mail`, `photo`, `number`, `date`)
VALUES ('аваіавіа', 'pavlenko', 'login', 'password', 'admin',  'alexander.pavlenko14@gmail.com', '/pages/img/icon/default.jpg', '+380507387976', '2017-10-21');

-- Add subjects ---------------------------------------------------------------
INSERT INTO subject(`name`, `icon`)
VALUE ('Java', '/pages/img/subjectIcons/java.png');
INSERT INTO subject(`name`, `icon`)
VALUE ('C++', '/pages/img/subjectIcons/cpp.png');
INSERT INTO subject(`name`, `icon`)
VALUE ('C#', '/pages/img/subjectIcons/cs.png');
INSERT INTO subject(`name`, `icon`)
VALUE ('HTML', '/pages/img/subjectIcons/html.png');
INSERT INTO subject(`name`, `icon`)
VALUE ('JS', '/pages/img/subjectIcons/js.png');
INSERT INTO subject(`name`, `icon`)
VALUE ('PHP', '/pages/img/subjectIcons/php.png');
-- --------------------------------------------------------------------------------

INSERT INTO test(`name`, `subject_id`, `description`, `type`, `icon`, `test_time`)
VALUE ('Java Core', 1, 'someDescription', 'module', 'icon.icon', 20);
INSERT INTO test(`name`, `subject_id`, `description`, `type`, `icon`, `test_time`)
VALUE ('While', 1, 'someDescription', 'topic', 'icon.icon', 20);

INSERT INTO question(`question`, `test_id`, `code`)
VALUE ('Укажите строку, которую нужно вписать в тело comparator-а (вместо ...), чтобы ключи в treeMap
были отсортированы в порядке возрастания(COM1 COM2 COM8 и т.д.).', 1, 'public class Main {

    public static void main(String[] args) {
        Comparator<String> comparator = new Comparator<String>() {
                @Override
                public int compare(String string_1, String string_2) {
                    ...
                }
        };

        TreeMap<String, String> treeMap = new TreeMap<String, String>(comparator);
        treeMap.put("COM1", "\\Device\\Serial0");
        treeMap.put("COM2", "\\Device\\Serial1");
        treeMap.put("COM8", "\\Device\\Nmserial0");
        treeMap.put("COM9", "\\Device\\Nmserial1");
        treeMap.put("COM10", "\\Device\\Nmserial2");
        treeMap.put("COM11", "\\Device\\Nmserial3");
    }
}');
INSERT INTO question(`question`, `test_id`, `code`)
VALUE ('Укажите строку, которую нужно вписать в тело comparator-а (вместо ...), чтобы ключи в treeMap
были отсортированы в порядке возрастания(COM1 COM2 COM8 и т.д.).', 1, 'public class Main {



        treeMap.put("COM9", "\\Device\\Nmserial1");
        treeMap.put("COM10", "\\Device\\Nmserial2");
        treeMap.put("COM11", "\\Device\\Nmserial3");
    }
}');

INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE ( 2, 'return string_1.compareTo(string_2)',TRUE);
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (2, 'return string_1.replace("COM", "").compareTo(string_2.replace("COM", ""));',TRUE);
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (2, 'return Integer.valueOf(string_1.replace("COM", "")) - Integer.valueOf(string_2.replace("COM", ""));',FALSE );
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (2, 'В использовании сomparator-а нет необходимости, ключи будут отсортированы в таком порядке автоматически',FALSE);









INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE ( 1, 'return string_1.compareTo(string_2)',TRUE);
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (1, 'return string_1.replace("COM", "").compareTo(string_2.replace("COM", ""));',FALSE);
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (1, 'return Integer.valueOf(string_1.replace("COM", "")) - Integer.valueOf(string_2.replace("COM", ""));',FALSE );
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (1, 'В использовании сomparator-а нет необходимости, ключи будут отсортированы в таком порядке автоматически',FALSE);
INSERT INTO answer(`question_id`, `answer`, `truthful`)
VALUE (1, 'return Integer.valueOf(string_1.replace("COM", "")) > Integer.valueOf(string_2.replace("COM", ""));',FALSE);


INSERT INTO user_resultat(`user_id`, `result_field`, `date_start`, `date_finish`)
VALUE (1, 100, '2017-10-21',  '2017-10-21');

INSERT INTO user_answer(`answer_id`, `user_resultat_id`)
VALUE (1, 1);
