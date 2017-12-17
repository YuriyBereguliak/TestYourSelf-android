package ua.edu.nulp.testyourself.model.defs;

import android.support.annotation.IntDef;

/**
 *  TestYourSelf-android
 * Created by Yuriy Bereguliak on 17.12.2017.
 */

@IntDef({
        QuestionType.MULTI,
        QuestionType.SINGLE,
        QuestionType.WRITE
})
public @interface QuestionType {
    int SINGLE = 0;
    int MULTI = 1;
    int WRITE = 3;
}
