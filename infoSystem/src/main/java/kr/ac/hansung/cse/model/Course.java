package kr.ac.hansung.cse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {
    private int id;
    private int totalDegree;

    private String username;

    @NotNull(message="수강년도를 입력해 주세요.")
    @Digits(integer = 4, fraction = 0, message = "4자리 숫자를 입력해 주세요.")
    private Integer year;

    @NotNull(message="학기를 입력해 주세요.")
    @Digits(integer = 1, fraction = 0, message = "1자리 숫자를 입력해 주세요.")
    private Integer semester;

    @NotEmpty(message="과목코드를 입력해 주세요.")
    private String code;

    @NotEmpty(message="과목명을 입력해 주세요.")
    private String name;

    @NotEmpty(message="교과구분을 입력해 주세요.")
    private String division;

    @NotEmpty(message="담당교수 이름을 입력해 주세요.")
    private String prof;

    @NotNull(message="학점을 입력해 주세요.")
    @Digits(integer = 1, fraction = 0)
    private Integer degree;
}
