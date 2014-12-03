package form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserForm {

    // メッセージはここで指定することも出来る
//    @NotEmpty(message = "名前が入力されていません")
    @NotEmpty(message = "{NotEmpty.name}")
    @Size(max = 10, message = "{Size.name}")
    private String name;

    @NotNull(message = "{NotNull.sex}")
    private Sex sex;

    private Boolean pregnant;

    public UserForm() {
    }

    @AssertTrue(message = "{AssertTrue.sexAndPregnant}")
    public boolean isValidPregnant() {

        // 性別が入力されていない時は、そちらで引っかかるのでバリデーションしない
        if (sex == null) {
            return true;
        }

        // 妊娠していると選択している人が女性であることをチェック
        if (pregnant) {
            return Sex.FEMALE == sex;
        }
        return true;
    }


    // accessors

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Boolean getPregnant() {
        return pregnant;
    }

    public void setPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }
}
