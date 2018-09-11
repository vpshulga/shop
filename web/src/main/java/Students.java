import com.gmail.vpshulgaa.dao.students.dao.entities.RecordBook;
import com.gmail.vpshulgaa.dao.students.dao.entities.Student;
import com.gmail.vpshulgaa.dao.students.dao.entities.Subject;
import com.gmail.vpshulgaa.dao.students.dao.entities.University;
import com.gmail.vpshulgaa.service.students.service.UniversityService;
import com.gmail.vpshulgaa.service.students.service.impl.UniversityServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Students {
    public static void main(String[] args) {
        GenerateStartData();
    }

    private static void GenerateStartData() {
        UniversityService universiyService = new UniversityServiceImpl();

        Student student1 = new Student();
        RecordBook recordBook1 = new RecordBook();
        Student student2 = new Student();
        RecordBook recordBook2 = new RecordBook();
        Student student3 = new Student();
        RecordBook recordBook3 = new RecordBook();
        Student student4 = new Student();
        RecordBook recordBook4 = new RecordBook();
        Student student5 = new Student();
        RecordBook recordBook5 = new RecordBook();
        Student student6 = new Student();
        RecordBook recordBook6 = new RecordBook();
        Student student7 = new Student();
        RecordBook recordBook7 = new RecordBook();
        Student student8 = new Student();
        RecordBook recordBook8 = new RecordBook();
        Student student9 = new Student();
        RecordBook recordBook9 = new RecordBook();
        Student student10 = new Student();
        RecordBook recordBook10 = new RecordBook();


        student1.setSurname("Petrov");
        student1.setName("Petr");
        student1.setPatronymic("Petrovich");
        student1.setBirthday(LocalDate.of(1990, 10, 13));
        recordBook1.setInvNumber("SB10983");
        recordBook1.setAvgMark(BigDecimal.valueOf(6.31));
        recordBook1.setLastChange(LocalDateTime.of(2018, 5, 10, 12, 47));
        recordBook1.setStudent(student1);
        student1.setRecordBook(recordBook1);


        student2.setSurname("Ivanov");
        student2.setName("Ivan");
        student2.setPatronymic("Ivanovich");
        student2.setBirthday(LocalDate.of(1992, 1, 14));
        recordBook2.setInvNumber("SB111112");
        recordBook2.setAvgMark(BigDecimal.valueOf(8.12));
        recordBook2.setLastChange(LocalDateTime.of(2018, 5, 6, 9, 34));
        recordBook2.setStudent(student2);
        student2.setRecordBook(recordBook2);

        student3.setSurname("Sidorov");
        student3.setName("Sidor");
        student3.setPatronymic("Sidorovich");
        student3.setBirthday(LocalDate.of(1994, 7, 18));
        recordBook3.setInvNumber("SB333212");
        recordBook3.setAvgMark(BigDecimal.valueOf(3.42));
        recordBook3.setLastChange(LocalDateTime.of(2018, 3, 6, 17, 34));
        recordBook3.setStudent(student3);
        student3.setRecordBook(recordBook3);

        student4.setSurname("Viktorov");
        student4.setName("Viktor");
        student4.setPatronymic("Viktorovich");
        student4.setBirthday(LocalDate.of(1993, 8, 6));
        recordBook4.setInvNumber("SB424432");
        recordBook4.setAvgMark(BigDecimal.valueOf(5.56));
        recordBook4.setLastChange(LocalDateTime.of(2018, 9, 1, 16, 41));
        recordBook4.setStudent(student4);
        student4.setRecordBook(recordBook4);

        student5.setSurname("Marinina");
        student5.setName("Marina");
        student5.setPatronymic("Ivanovna");
        student5.setBirthday(LocalDate.of(1993, 7, 14));
        recordBook5.setInvNumber("SB199112");
        recordBook5.setAvgMark(BigDecimal.valueOf(9.1));
        recordBook5.setLastChange(LocalDateTime.of(2018, 5, 6, 9, 34));
        recordBook5.setStudent(student5);
        student5.setRecordBook(recordBook5);

        student6.setSurname("Malahov");
        student6.setName("Andrey");
        student6.setPatronymic("Igorevich");
        student6.setBirthday(LocalDate.of(1994, 3, 17));
        recordBook6.setInvNumber("SB117712");
        recordBook6.setAvgMark(BigDecimal.valueOf(8.12));
        recordBook6.setLastChange(LocalDateTime.of(2018, 5, 6, 19, 34));
        recordBook6.setStudent(student6);
        student6.setRecordBook(recordBook6);

        student7.setSurname("Serov");
        student7.setName("Vlad");
        student7.setPatronymic("Petrovich");
        student7.setBirthday(LocalDate.of(1991, 11, 11));
        recordBook7.setInvNumber("SB123822");
        recordBook7.setAvgMark(BigDecimal.valueOf(3.12));
        recordBook7.setLastChange(LocalDateTime.of(2018, 5, 6, 9, 34));
        recordBook7.setStudent(student7);
        student7.setRecordBook(recordBook7);

        student8.setSurname("Silnov");
        student8.setName("Leonid");
        student8.setPatronymic("Ivanovich");
        student8.setBirthday(LocalDate.of(1996, 4, 24));
        recordBook8.setInvNumber("SB116112");
        recordBook8.setAvgMark(BigDecimal.valueOf(7.68));
        recordBook8.setLastChange(LocalDateTime.of(2018, 6, 5, 19, 34));
        recordBook8.setStudent(student8);
        student8.setRecordBook(recordBook8);

        student9.setSurname("Sharapova");
        student9.setName("Mariya");
        student9.setPatronymic("Ivanovna");
        student9.setBirthday(LocalDate.of(1993, 12, 4));
        recordBook9.setInvNumber("SB145112");
        recordBook9.setAvgMark(BigDecimal.valueOf(6.41));
        recordBook9.setLastChange(LocalDateTime.of(2018, 5, 6, 9, 34));
        recordBook9.setStudent(student9);
        student9.setRecordBook(recordBook9);

        student10.setSurname("Titov");
        student10.setName("Alexey");
        student10.setPatronymic("Petrovich");
        student10.setBirthday(LocalDate.of(1994, 8, 14));
        recordBook10.setInvNumber("SB1147812");
        recordBook10.setAvgMark(BigDecimal.valueOf(4.2));
        recordBook10.setLastChange(LocalDateTime.of(2018, 6, 6, 9, 34));
        recordBook10.setStudent(student10);
        student10.setRecordBook(recordBook10);



        University bsuir = new University();
        University bntu = new University();
        University bgeu = new University();
        University bgupc = new University();



        bsuir.setName("Belorussian state university of informatics and radioelectronics");
        bsuir.setShortName("BSUIR");
        bsuir.setWorldRate(BigDecimal.valueOf(4.32));
        bsuir.getStudents().add(student1);
        bsuir.getStudents().add(student2);

        bsuir.setName("Belorussian state technical university");
        bsuir.setShortName("BSUIR");
        bsuir.setWorldRate(BigDecimal.valueOf(4.32));
        bsuir.getStudents().add(student1);
        bsuir.getStudents().add(student2);

        bsuir.setName("Belorussian state university of informatics and radioelectronics");
        bsuir.setShortName("BSUIR");
        bsuir.setWorldRate(BigDecimal.valueOf(4.32));
        bsuir.getStudents().add(student1);
        bsuir.getStudents().add(student2);

        bsuir.setName("Belorussian state university of informatics and radioelectronics");
        bsuir.setShortName("BSUIR");
        bsuir.setWorldRate(BigDecimal.valueOf(4.32));
        bsuir.getStudents().add(student1);
        bsuir.getStudents().add(student2);


        Subject mathematics = new Subject();
        Subject phisics = new Subject();
        Subject literature = new Subject();
        Subject history = new Subject();
        Subject biology = new Subject();
        Subject ideology =  new Subject();

        mathematics.setName("Mathematics");
        mathematics.setDifficulty(BigDecimal.valueOf(8.31));

        phisics.setName("Phisics");
        phisics.setDifficulty(BigDecimal.valueOf(9.2));

        literature.setName("Literature");
        literature.setDifficulty(BigDecimal.valueOf(4.2));

        history.setName("History");
        history.setDifficulty(BigDecimal.valueOf(5.69));

        biology.setName("Biology");
        biology.setDifficulty(BigDecimal.valueOf(5.28));

        ideology.setName("Ideology");
        ideology.setDifficulty(BigDecimal.valueOf(3.12));

        student1.getSubjects().add(mathematics);
        student1.getSubjects().add(phisics);
        student1.getSubjects().add(biology);
        student1.getSubjects().add(ideology);

        student2.getSubjects().add(mathematics);
        student2.getSubjects().add(phisics);
        student2.getSubjects().add(literature);
        student2.getSubjects().add(history);
        student2.getSubjects().add(biology);

        mathematics.getStudents().add(student1);
        mathematics.getStudents().add(student2);

        phisics.getStudents().add(student1);
        phisics.getStudents().add(student2);

        literature.getStudents().add(student2);

        history.getStudents().add(student2);

        biology.getStudents().add(student1);
        biology.getStudents().add(student2);

        ideology.getStudents().add(student1);


        universiyService.create(bsuir);
    }


}
