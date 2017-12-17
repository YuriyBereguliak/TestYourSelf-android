package ua.edu.nulp.testyourself.model.defs;

import android.support.annotation.IntDef;

/**
 *  TestYourSelf-android
 * Created by Yuriy Bereguliak on 17.12.2017.
 */

@IntDef({
        QuestionType.INSERT,
        QuestionType.MULTI,
        QuestionType.SINGLE,
        QuestionType.TRANSLATE,
        QuestionType.WRITE
})
public @interface QuestionType {
    int SINGLE = 0;
    int MULTI = 1;
    int INSERT = 2;
    int WRITE = 3;
    int TRANSLATE = 4;
}
