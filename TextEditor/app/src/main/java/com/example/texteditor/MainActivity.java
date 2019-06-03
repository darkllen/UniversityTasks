package com.example.texteditor;

        import android.annotation.SuppressLint;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.BlurMaskFilter;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.Html;
        import android.text.Layout;
        import android.text.SpannableStringBuilder;
        import android.text.Spanned;
        import android.text.TextWatcher;
        import android.text.style.AbsoluteSizeSpan;
        import android.text.style.AlignmentSpan;
        import android.text.style.BackgroundColorSpan;
        import android.text.style.ForegroundColorSpan;
        import android.text.style.MaskFilterSpan;
        import android.text.style.StrikethroughSpan;
        import android.text.style.StyleSpan;
        import android.text.style.SubscriptSpan;
        import android.text.style.SuperscriptSpan;
        import android.text.style.TypefaceSpan;
        import android.text.style.UnderlineSpan;
        import android.util.ArraySet;
        import android.util.TypedValue;
        import android.view.ActionMode;
        import android.view.KeyEvent;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.AbsoluteLayout;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.CompoundButton;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.Spinner;
        import android.widget.Switch;

        import com.azeesoft.lib.colorpicker.ColorPickerDialog;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Objects;
        import java.util.Set;
        import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private float[] lastTouchDownXY = new float[2];
    ArrayList<EditText> editTexts = new ArrayList<>();

    boolean bold = false;
    boolean italic = false;
    boolean underline = false;
    boolean crossed = false;
    boolean lowerRegister = false;
    boolean upperRegister = false;
    boolean blur = false;
    boolean casual = false;
    boolean cursive = false;
    boolean monospace = false;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        LayoutInflater inflater = LayoutInflater.from(this);
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        ViewPager viewPager = new ViewPager(this);

        List<View> pages = new ArrayList<View>();


        @SuppressLint("InflateParams") View page1 = inflater.inflate(R.layout.page, null);
        @SuppressLint("InflateParams") View page = inflater.inflate(R.layout.activity_main, null);
        @SuppressLint("InflateParams") View pageSettings = inflater.inflate(R.layout.settings, null);



        pages.add(page1);
        pages.add(page);
        pages.add(pageSettings);

        Spinner spinner = pageSettings.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        cursive = true;
                        monospace = false;
                        casual = false;
                        break;
                    case 2:
                        cursive = false;
                        monospace = true;
                        casual = false;
                        break;

                    default:
                        cursive = false;
                        monospace = false;
                        casual = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Switch switchUnderline = pageSettings.findViewById(R.id.underline);
        switchUnderline.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) underline = true;
                else underline = false;
            }
        });

        Switch switchBold = pageSettings.findViewById(R.id.bold);
        switchBold.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) bold = true;
                else bold = false;
            }
        });

        Switch switchBlur = pageSettings.findViewById(R.id.blur);
        switchBlur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) blur = true;
                else blur = false;
            }
        });

        Switch switchItalics = pageSettings.findViewById(R.id.italics);
        switchItalics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) italic = true;
                else italic = false;
            }
        });
        Switch switchCrossed = pageSettings.findViewById(R.id.crossed);
        switchCrossed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) crossed = true;
                else crossed = false;
            }
        });
        Switch switchUpperRegister = pageSettings.findViewById(R.id.upperRegister);
        switchUpperRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) lowerRegister = true;
                else lowerRegister = false;
            }
        });
        Switch switchLowerRegister= pageSettings.findViewById(R.id.lowerRegister);
        switchLowerRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) upperRegister = true;
                else upperRegister = false;
            }
        });



        ColorPickerDialog colorPickerDialogForeground= ColorPickerDialog.createColorPickerDialog(this,ColorPickerDialog.DARK_THEME);
        ColorPickerDialog colorPickerDialogBackground= ColorPickerDialog.createColorPickerDialog(this,ColorPickerDialog.DARK_THEME);
        colorPickerDialogForeground.setInitialColor(Color.BLACK);
        colorPickerDialogBackground.setInitialColor(Color.WHITE);

        Button foreground = pageSettings.findViewById(R.id.foreground);
        foreground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickerDialogForeground.show();
            }
        });

        Button background = pageSettings.findViewById(R.id.background);
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorPickerDialogBackground.show();
            }
        });


        LinearLayout linearLayout = page1.findViewById(R.id.aline);

        Set<String> names = mPrefs.getStringSet("names", null);
        List<String> sortedStrings = null;
        if (names != null) {
            sortedStrings = new ArrayList<>(names);
            sortedStrings.forEach(x->x.toLowerCase());
            Collections.sort(sortedStrings);
        }

        if (sortedStrings!=null) {
            for (String name: sortedStrings) {
                Button button = new Button(page.getContext());
                button.setText(name);
                button.setOnClickListener(new BuildFromRecordsOnClick(name,page,viewPager,pageSettings, colorPickerDialogForeground, colorPickerDialogBackground));
                linearLayout.addView(button);
            }
        }

        AbsoluteLayout abs = page.findViewById(R.id.absol);
        abs.setOnTouchListener(new CoordinatesRecordOnTouchListener());
        abs.setOnClickListener(new CreateEditTextOnClick(pageSettings, colorPickerDialogForeground, colorPickerDialogBackground));

        Button button = page.findViewById(R.id.button2);
        button.setOnClickListener(new SaveOnClickListener(page,viewPager,linearLayout, pageSettings, colorPickerDialogForeground, colorPickerDialogBackground));
        Button button2 = page.findViewById(R.id.button4);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (EditText e:editTexts) {
                    abs.removeView(e);
                }
                editTexts = new ArrayList<EditText>();
                EditText editText = findViewById(R.id.editText3);
                editText.setText("Name");
            }
        });

        Button buttonDelete = page.findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = page.findViewById(R.id.editText3);
                String name = editText.getText().toString();
                SharedPreferences mPrefs1 = getPreferences(MODE_PRIVATE);
                Set<String> json = mPrefs1.getStringSet("names", null);
                Set<String> newSet = new ArraySet<>();
                newSet.addAll(json);
                newSet.remove(name);
                SharedPreferences.Editor prefsEditor = mPrefs1.edit();
                prefsEditor.putStringSet("names", newSet);
                prefsEditor.commit();
                json = mPrefs1.getStringSet("names", null);
                for (EditText e:editTexts) {
                    abs.removeView(e);
                }
                editTexts = new ArrayList<EditText>();
                editText.setText("Name");
            }
        });
        Button buttonNew = page1.findViewById(R.id.newText);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = page.findViewById(R.id.editText3);
                for (EditText e:editTexts) {
                    abs.removeView(e);
                }
                editTexts = new ArrayList<EditText>();
                editText.setText("Name");
                viewPager.setCurrentItem(1);

            }
        });

        SamplePagerAdapter pagerAdapter = new SamplePagerAdapter(pages);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        setContentView(viewPager);
    }




    class BuildFromRecordsOnClick implements View.OnClickListener{
        public BuildFromRecordsOnClick(String name, View page, ViewPager viewPager, View pageSettings, ColorPickerDialog foregroundDialog, ColorPickerDialog backgroundDialog) {
            this.name = name;
            this.page = page;
            this.viewPager = viewPager;
            this.pageSettings = pageSettings;
            this.foregroundDialog = foregroundDialog;
            this.backgroundDialog = backgroundDialog;
        }
        View pageSettings;
        ViewPager viewPager;
        String name;
        View page;
        ColorPickerDialog foregroundDialog;
        ColorPickerDialog backgroundDialog;
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            SharedPreferences mPrefs1 = getPreferences(MODE_PRIVATE);
            Set<String> json = mPrefs1.getStringSet(name, null);



            AbsoluteLayout fl = page.findViewById(R.id.absol);
            for (EditText e:editTexts) {
                fl.removeView(e);
            }
            editTexts = new ArrayList<>();

            if (json != null) {
                for (String s: json) {
                    String[] strings = s.split("/../../../");
                    EditText editText = page.findViewById(R.id.editText3);
                    editText.setText(name);



                    AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT, (int) Double.parseDouble(strings[0]), (int) Double.parseDouble(strings[1]));

                    EditText tv1 = new EditText(v.getContext());
                    editTexts.add(tv1);
                    tv1.setLayoutParams(params);
                    String sa = strings[4];


                    Spanned saa =  Html.fromHtml(strings[4]);

                    ArraySet<Integer> bolds = new ArraySet();
                    ArraySet<Integer> italics = new ArraySet();
                    ArraySet<Integer> underlines = new ArraySet();
                    ArraySet<Integer> strikes = new ArraySet();
                    ArraySet<Integer> supers = new ArraySet();
                    ArraySet<Integer> subs = new ArraySet();
                    ArraySet<Integer> blurs = new ArraySet();
                    ArraySet<Integer> casuals = new ArraySet();
                    ArraySet<Integer> cursives = new ArraySet();
                    ArraySet<Integer> monospaces = new ArraySet();
                    HashMap<Integer, Integer> sizes = new HashMap<>();
                    HashMap<Integer, Integer> foregroundColors = new HashMap<>();
                    HashMap<Integer, Integer> backgroundColors = new HashMap<>();

                    StyleSpan[] styleSpans = saa.getSpans(0,strings[4].length(),StyleSpan.class);
                    for (StyleSpan spa:styleSpans) {
                        if (spa.getStyle()==Typeface.BOLD){
                            int start = saa.getSpanStart(spa);
                            bolds.add(start);
                        }
                        if (spa.getStyle()==Typeface.ITALIC){
                            int start = saa.getSpanStart(spa);
                            italics.add(start);
                        }
                    }
                    UnderlineSpan[] underlineSpans = saa.getSpans(0,strings[4].length(),UnderlineSpan.class);
                    for (UnderlineSpan spa:underlineSpans) {
                        int start = saa.getSpanStart(spa);
                        underlines.add(start);
                    }

                    StrikethroughSpan[] strikethroughSpans = saa.getSpans(0,strings[4].length(),StrikethroughSpan.class);
                    for (StrikethroughSpan spa:strikethroughSpans) {
                        int start = saa.getSpanStart(spa);
                        strikes.add(start);
                    }
                    SuperscriptSpan[] superscriptSpans = saa.getSpans(0,strings[4].length(),SuperscriptSpan.class);
                    for (SuperscriptSpan spa:superscriptSpans) {
                        int start = saa.getSpanStart(spa);
                        supers.add(start);
                    }
                    SubscriptSpan[] subscriptSpans = saa.getSpans(0,strings[4].length(),SubscriptSpan.class);
                    for (SubscriptSpan spa:subscriptSpans) {
                        int start = saa.getSpanStart(spa);
                        subs.add(start);
                    }
                    MaskFilterSpan[] blurSpans = saa.getSpans(0,strings[4].length(),MaskFilterSpan.class);
                    for (MaskFilterSpan spa:blurSpans) {
                        int start = saa.getSpanStart(spa);
                        blurs.add(start);
                    }
                    TypefaceSpan[] typefaceSpans = saa.getSpans(0,strings[4].length(),TypefaceSpan.class);
                    for (TypefaceSpan spa:typefaceSpans) {
                        if (spa.getFamily().equals("serif")){
                            int start = saa.getSpanStart(spa);
                            casuals.add(start);
                        }
                        if (spa.getFamily().equals("cursive")){
                            int start = saa.getSpanStart(spa);
                            cursives.add(start);
                        }
                        if (spa.getFamily().equals("monospace")){
                            int start = saa.getSpanStart(spa);
                            monospaces.add(start);
                        }
                    }
                    AbsoluteSizeSpan[] sizeSpan = saa.getSpans(0,strings[4].length(),AbsoluteSizeSpan.class);
                    for (AbsoluteSizeSpan spa:sizeSpan) {
                        int start = saa.getSpanStart(spa);
                        sizes.put(start,spa.getSize());
                    }
                    ForegroundColorSpan[] foregroundColorSpans = saa.getSpans(0,strings[4].length(),ForegroundColorSpan.class);
                    for (ForegroundColorSpan spa:foregroundColorSpans) {
                        int start = saa.getSpanStart(spa);
                        foregroundColors.put(start,spa.getForegroundColor());
                    }
                    BackgroundColorSpan[] backgroundColorSpans = saa.getSpans(0,strings[4].length(),BackgroundColorSpan.class);
                    for (BackgroundColorSpan spa:backgroundColorSpans) {
                        int start = saa.getSpanStart(spa);
                        backgroundColors.put(start,spa.getBackgroundColor());
                    }

                    String alignment = "left";
                    if (strings[7].equals("ALIGN_CENTER"))alignment = "center";
                    if (strings[7].equals("ALIGN_RIGHT"))alignment = "right";

                    if (saa.length()!=0){
                        tv1.setText(   saa.subSequence(0,saa.length()-2));
                    } else
                        tv1.setText("");
                    tv1.setMaxWidth((int) Double.parseDouble(strings[5]));
                    tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX,(float) Double.parseDouble(strings[6]));
                    tv1.setTextColor(Color.BLACK);
                    tv1.setOnKeyListener(new DeleteOnBackKey(tv1,fl));

                    final stylesCreateOnTextChangedListener[] textWatcher = new stylesCreateOnTextChangedListener[1];
                    if (saa.length()!=0){
                        textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,
                                saa.subSequence(0,saa.length()-2).toString(),foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                    } else
                        textWatcher[0] =  new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,
                                "",foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);



                    tv1.addTextChangedListener(textWatcher[0]);
                    tv1.append("s");
                    tv1.setText(tv1.getText().subSequence(0,tv1.getText().length()-1));
                    tv1.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater inflater = mode.getMenuInflater();
                            inflater.inflate(R.menu.menu_settings,menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return false;
                        }

                        @RequiresApi(api = Build.VERSION_CODES.P)
                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                            EditText size = pageSettings.findViewById(R.id.editTextNumber);
                            String strings  = Html.toHtml(tv1.getText());
                            Spanned saa =  Html.fromHtml(Html.toHtml(tv1.getText()));

                            String alignment = textWatcher[0].aliment;
                            ArraySet<Integer> bolds = textWatcher[0].getBolds();
                            ArraySet<Integer> italics = textWatcher[0].getItalics();
                            ArraySet<Integer> underlines = textWatcher[0].getUnderlines();
                            ArraySet<Integer> strikes = textWatcher[0].getCrosseds();
                            ArraySet<Integer> supers = textWatcher[0].getUpperRegisters();
                            ArraySet<Integer> subs = textWatcher[0].getLowerRegisters();
                            ArraySet<Integer> blurs = textWatcher[0].getBlurs();
                            ArraySet<Integer> casuals = textWatcher[0].getCasuals();
                            ArraySet<Integer> cursives = textWatcher[0].getCursives();
                            ArraySet<Integer> monospaces = textWatcher[0].getMonospaces();
                            HashMap<Integer, Integer> sizes = textWatcher[0].getSizes();
                            HashMap<Integer, Integer> foregroundColors = textWatcher[0].getForegroundColors();
                            HashMap<Integer, Integer> backgroundColors =textWatcher[0].getBackgroundColors();

                            int start = tv1.getSelectionStart();
                            int end = tv1.getSelectionEnd();
                            switch (item.getItemId()) {

                                case R.id.B:

                                    for (int i = start;i<end;i++){
                                        bolds.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.U:
                                    for (int i = start;i<end;i++){
                                        underlines.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.I:

                                    for (int i = start;i<end;i++){
                                        italics.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.Blur:
                                    for (int i = start;i<end;i++){
                                        blurs.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new MaskFilterSpan(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.crossed:

                                    for (int i = start;i<end;i++){
                                        strikes.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.MB:

                                    for (int i = start;i<end;i++){
                                        bolds.remove(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.MU:

                                    for (int i = start;i<end;i++){
                                        underlines.remove(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.MI:

                                    for (int i = start;i<end;i++){
                                        italics.remove(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.Mcrossed:
                                    for (int i = start;i<end;i++){
                                        strikes.remove(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.MBlur:

                                    for (int i = start;i<end;i++){
                                        blurs.remove(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.upperRegister:
                                    for (int i = start;i<end;i++){
                                        supers.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.lowerRegister:

                                    for (int i = start;i<end;i++){
                                        subs.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.foreground:
                                    for (int i = start;i<end;i++){
                                        foregroundColors.put(i, foregroundDialog.getCurrentColor());
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new ForegroundColorSpan(foregroundDialog.getCurrentColor()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.background:
                                    for (int i = start;i<end;i++){
                                        backgroundColors.put(i, backgroundDialog.getCurrentColor());
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new BackgroundColorSpan(backgroundDialog.getCurrentColor()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.size:
                                    int textSize = 50;
                                    if (size.getText().length()!=0)textSize = Integer.parseInt(String.valueOf(size.getText()));
                                    for (int i = start;i<end;i++){
                                        sizes.put(i,textSize);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new AbsoluteSizeSpan(textSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.Casual:
                                    for (int i = start;i<end;i++){
                                        casuals.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new TypefaceSpan("serif"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.cursive:
                                    for (int i = start;i<end;i++){
                                        cursives.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new TypefaceSpan("cursive"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.monospace:
                                    for (int i = start;i<end;i++){
                                        monospaces.add(i);
                                    }
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.getEditableText().setSpan(new TypefaceSpan("monospace"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                default:
                                    return false;
                            }
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {

                        }
                    });
                    tv1.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater inflater = mode.getMenuInflater();
                            inflater.inflate(R.menu.menu_settings_second,menu);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            return false;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                            EditText size = pageSettings.findViewById(R.id.editTextNumber);
                            String strings  = Html.toHtml(tv1.getText());
                            Spanned saa =  Html.fromHtml(Html.toHtml(tv1.getText()));

                            ArraySet<Integer> bolds = textWatcher[0].getBolds();
                            ArraySet<Integer> italics = textWatcher[0].getItalics();
                            ArraySet<Integer> underlines = textWatcher[0].getUnderlines();
                            ArraySet<Integer> strikes = textWatcher[0].getCrosseds();
                            ArraySet<Integer> supers = textWatcher[0].getUpperRegisters();
                            ArraySet<Integer> subs = textWatcher[0].getLowerRegisters();
                            ArraySet<Integer> blurs = textWatcher[0].getBlurs();
                            ArraySet<Integer> casuals = textWatcher[0].getCasuals();
                            ArraySet<Integer> cursives = textWatcher[0].getCursives();
                            ArraySet<Integer> monospaces = textWatcher[0].getMonospaces();
                            HashMap<Integer, Integer> sizes = textWatcher[0].getSizes();
                            HashMap<Integer, Integer> foregroundColors = textWatcher[0].getForegroundColors();
                            HashMap<Integer, Integer> backgroundColors =textWatcher[0].getBackgroundColors();

                            int start = tv1.getSelectionStart();
                            int end = tv1.getSelectionEnd();
                            switch (item.getItemId()) {

                                case R.id.Aleft:
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, "left", pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.Acenter:
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, "center", pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                case R.id.Aright:
                                    tv1.removeTextChangedListener(textWatcher[0]);
                                    tv1.setText(   saa.subSequence(0,saa.length()-2));
                                    tv1.setSelection(end);
                                    textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                            foregroundDialog, backgroundDialog, foregroundColors, backgroundColors, "right", pageSettings);
                                    tv1.addTextChangedListener(textWatcher[0]);
                                    tv1.append("s");
                                    tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                                    return true;
                                default:
                                    return false;
                            }
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {

                        }
                    });

                    fl.addView(tv1);
                }
            }
            viewPager.setCurrentItem(1);
        }
    }

    class DeleteOnBackKey implements View.OnKeyListener{
        EditText tv1;
        AbsoluteLayout fl;

        public DeleteOnBackKey(EditText tv1, AbsoluteLayout absoluteLayout) {
            this.tv1 = tv1;
            this.fl = absoluteLayout;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode==66 || keyCode==4 || tv1.getText().toString().length()!=0) return false;
            editTexts.remove(v);
            fl.removeView(v);
            if (editTexts.size()!=0) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTexts.get(editTexts.size() - 1), InputMethodManager.SHOW_IMPLICIT);
                editTexts.get(editTexts.size() - 1).requestFocus();
                editTexts.get(editTexts.size() - 1).setSelection(editTexts.get(editTexts.size() - 1).getText().length());
            }
            return true;
        }
    }

    class SaveOnClickListener implements View.OnClickListener{
        View page;
        View pageSettings;
        ViewPager viewPager;
        LinearLayout linearLayout;
        ColorPickerDialog foregroundDialog;
        ColorPickerDialog backgroundDialog;
        public SaveOnClickListener(View page, ViewPager viewPager, LinearLayout linearLayout, View pageSettings, ColorPickerDialog foregroundDialog, ColorPickerDialog backgroundDialog) {
            this.page = page;
            this.viewPager = viewPager;
            this.linearLayout = linearLayout;
            this.pageSettings = pageSettings;
            this.foregroundDialog = foregroundDialog;
            this.backgroundDialog = backgroundDialog;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            if (editTexts.size() != 0){
                SharedPreferences mPrefs12 = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs12.edit();
                Set<String> strings = new ArraySet<>();
                for (int i = 0; i < editTexts.size(); i++) {
                    EditText editText = editTexts.get(i);
                    AlignmentSpan.Standard[] spans = editText.getText().getSpans(0, editText.getText().length(), AlignmentSpan.Standard.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        strings.add(editText.getX() + "/../../../" + editText.getY() + "/../../../" + editText.getWidth() + "/../../../" + editText.getHeight() + "/../../../" + Html.toHtml(editText.getText()) + "/../../../"
                                + editText.getMaxWidth() + "/../../../" + editText.getTextSize() + "/../../../" + spans[0].getAlignment().name()) ;
                    }
                }
                EditText name = page.findViewById(R.id.editText3);
                Set<String> stringName = mPrefs12.getStringSet("names", new ArraySet<>());

                if (!stringName.contains(name.getText().toString())){
                    Button button = new Button(page.getContext());
                    button.setText(name.getText().toString());
                    button.setOnClickListener(new BuildFromRecordsOnClick(name.getText().toString(), page, viewPager, pageSettings, foregroundDialog, backgroundDialog));
                    linearLayout.addView(button);
                }
                stringName.add(name.getText().toString());



                prefsEditor.putStringSet("names", stringName);
                prefsEditor.putStringSet(name.getText().toString(), strings);
                prefsEditor.apply();


            }
        }
    }


    class CoordinatesRecordOnTouchListener implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                lastTouchDownXY[0] = event.getX();
                lastTouchDownXY[1] = event.getY();
            }
            return false;
        }
    }



    class CreateEditTextOnClick implements View.OnClickListener{
        View pageSettings = null;
        ColorPickerDialog foregroundColorDialog;
        ColorPickerDialog backgroundColorDialog;
        public CreateEditTextOnClick(View pageSettings, ColorPickerDialog foregroundColorDialog, ColorPickerDialog backgroundColorDialog) {
            this.pageSettings = pageSettings;
            this.foregroundColorDialog = foregroundColorDialog;
            this.backgroundColorDialog = backgroundColorDialog;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            int size = 20;
            EditText tv1 = new EditText(v.getContext());
            editTexts.add(tv1);

            AbsoluteLayout fl =findViewById(R.id.absol);
            AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.WRAP_CONTENT, AbsoluteLayout.LayoutParams.WRAP_CONTENT,(int)  lastTouchDownXY[0], (int) lastTouchDownXY[1]);

            tv1.setLayoutParams(params);
            tv1.setTextSize(size);
            tv1.setTextColor(Color.BLACK);
            tv1.setMaxWidth((int) (v.getWidth()-lastTouchDownXY[0]));
            tv1.requestFocus();

            tv1.setOnKeyListener(new DeleteOnBackKey(tv1,fl));

            final stylesCreateOnTextChangedListener[] textWatcher = {new stylesCreateOnTextChangedListener(tv1, pageSettings, foregroundColorDialog, "left", backgroundColorDialog)};
            tv1.addTextChangedListener(textWatcher[0]);
            tv1.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.menu_settings,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                    EditText size = pageSettings.findViewById(R.id.editTextNumber);
                    String strings  = Html.toHtml(tv1.getText());
                    Spanned saa =  Html.fromHtml(Html.toHtml(tv1.getText()));

                    String alignment = textWatcher[0].aliment;
                    ArraySet<Integer> bolds = textWatcher[0].getBolds();
                    ArraySet<Integer> italics = textWatcher[0].getItalics();
                    ArraySet<Integer> underlines = textWatcher[0].getUnderlines();
                    ArraySet<Integer> strikes = textWatcher[0].getCrosseds();
                    ArraySet<Integer> supers = textWatcher[0].getUpperRegisters();
                    ArraySet<Integer> subs = textWatcher[0].getLowerRegisters();
                    ArraySet<Integer> blurs = textWatcher[0].getBlurs();
                    ArraySet<Integer> casuals = textWatcher[0].getCasuals();
                    ArraySet<Integer> cursives = textWatcher[0].getCursives();
                    ArraySet<Integer> monospaces = textWatcher[0].getMonospaces();
                    HashMap<Integer, Integer> sizes = textWatcher[0].getSizes();
                    HashMap<Integer, Integer> foregroundColors = textWatcher[0].getForegroundColors();
                    HashMap<Integer, Integer> backgroundColors =textWatcher[0].getBackgroundColors();

                    int start = tv1.getSelectionStart();
                    int end = tv1.getSelectionEnd();
                    switch (item.getItemId()) {
                        case R.id.B:

                            for (int i = start;i<end;i++){
                                bolds.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.U:
                            for (int i = start;i<end;i++){
                                underlines.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.I:

                            for (int i = start;i<end;i++){
                                italics.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.Blur:
                            for (int i = start;i<end;i++){
                                blurs.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new MaskFilterSpan(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.crossed:

                            for (int i = start;i<end;i++){
                                strikes.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.MB:

                            for (int i = start;i<end;i++){
                                bolds.remove(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.MU:

                            for (int i = start;i<end;i++){
                                underlines.remove(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.MI:

                            for (int i = start;i<end;i++){
                                italics.remove(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.Mcrossed:
                            for (int i = start;i<end;i++){
                                strikes.remove(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.MBlur:

                            for (int i = start;i<end;i++){
                                blurs.remove(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.upperRegister:
                            for (int i = start;i<end;i++){
                                supers.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new SuperscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.lowerRegister:

                            for (int i = start;i<end;i++){
                                subs.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.foreground:
                            for (int i = start;i<end;i++){
                                foregroundColors.put(i, foregroundColorDialog.getCurrentColor());
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new ForegroundColorSpan(foregroundColorDialog.getCurrentColor()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.background:
                            for (int i = start;i<end;i++){
                                backgroundColors.put(i, backgroundColorDialog.getCurrentColor());
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new BackgroundColorSpan(backgroundColorDialog.getCurrentColor()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.size:
                            int textSize = 50;
                            if (size.getText().length()!=0)textSize = Integer.parseInt(String.valueOf(size.getText()));
                            for (int i = start;i<end;i++){
                                sizes.put(i,textSize);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new AbsoluteSizeSpan(textSize), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.Casual:
                            for (int i = start;i<end;i++){
                                casuals.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new TypefaceSpan("serif"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.cursive:
                            for (int i = start;i<end;i++){
                                cursives.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new TypefaceSpan("cursive"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.monospace:
                            for (int i = start;i<end;i++){
                                monospaces.add(i);
                            }
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.getEditableText().setSpan(new TypefaceSpan("monospace"), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, alignment, pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        default:
                            return false;
                    }
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
            tv1.setCustomInsertionActionModeCallback(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.menu_settings_second,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                    EditText size = pageSettings.findViewById(R.id.editTextNumber);
                    String strings  = Html.toHtml(tv1.getText());
                    Spanned saa =  Html.fromHtml(Html.toHtml(tv1.getText()));

                    String alignment = textWatcher[0].aliment;
                    ArraySet<Integer> bolds = textWatcher[0].getBolds();
                    ArraySet<Integer> italics = textWatcher[0].getItalics();
                    ArraySet<Integer> underlines = textWatcher[0].getUnderlines();
                    ArraySet<Integer> strikes = textWatcher[0].getCrosseds();
                    ArraySet<Integer> supers = textWatcher[0].getUpperRegisters();
                    ArraySet<Integer> subs = textWatcher[0].getLowerRegisters();
                    ArraySet<Integer> blurs = textWatcher[0].getBlurs();
                    ArraySet<Integer> casuals = textWatcher[0].getCasuals();
                    ArraySet<Integer> cursives = textWatcher[0].getCursives();
                    ArraySet<Integer> monospaces = textWatcher[0].getMonospaces();
                    HashMap<Integer, Integer> sizes = textWatcher[0].getSizes();
                    HashMap<Integer, Integer> foregroundColors = textWatcher[0].getForegroundColors();
                    HashMap<Integer, Integer> backgroundColors =textWatcher[0].getBackgroundColors();

                    int start = tv1.getSelectionStart();
                    int end = tv1.getSelectionEnd();
                    switch (item.getItemId()) {

                        case R.id.Aleft:
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, "left", pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.Acenter:
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, "center", pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        case R.id.Aright:
                            tv1.removeTextChangedListener(textWatcher[0]);
                            tv1.setText(   saa.subSequence(0,saa.length()-2));
                            tv1.setSelection(end);
                            textWatcher[0] = new stylesCreateOnTextChangedListener(bolds,italics,underlines,strikes,subs,supers,blurs,casuals,cursives,monospaces, sizes, tv1,  saa.subSequence(0,saa.length()-2).toString(),
                                    foregroundColorDialog, backgroundColorDialog, foregroundColors, backgroundColors, "right", pageSettings);
                            tv1.addTextChangedListener(textWatcher[0]);
                            tv1.append("s");
                            tv1.setText(tv1.getText().subSequence(0, tv1.getText().length()-1));
                            return true;
                        default:
                            return false;
                    }
                }
                @Override
                public void onDestroyActionMode(ActionMode mode) {
                }
            });
            fl.addView(tv1);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(tv1, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    class stylesCreateOnTextChangedListener implements TextWatcher{
        int start = 0;
        String last ="";
        String aliment = "";
        ArraySet<Integer> bolds = new ArraySet<>();
        ArraySet<Integer> italics = new ArraySet<>();
        ArraySet<Integer> underlines = new ArraySet<>();
        ArraySet<Integer> crosseds = new ArraySet<>();
        ArraySet<Integer> upperRegisters = new ArraySet<>();
        ArraySet<Integer> lowerRegisters = new ArraySet<>();
        ArraySet<Integer> blurs = new ArraySet<>();
        ArraySet<Integer> casuals = new ArraySet<>();
        ArraySet<Integer> cursives = new ArraySet<>();
        ArraySet<Integer> monospaces = new ArraySet<>();
        HashMap<Integer, Integer> sizes = new HashMap<>();
        HashMap<Integer, Integer> foregroundColors = new HashMap<>();
        HashMap<Integer, Integer> backgroundColors = new HashMap<>();
        EditText tv1;
        View pageSettings;
        ColorPickerDialog foregroundColorDialog;
        ColorPickerDialog backgroundColorDialog;

        public ArraySet<Integer> getBolds() {
            return bolds;
        }

        public ArraySet<Integer> getItalics() {
            return italics;
        }

        public ArraySet<Integer> getUnderlines() {
            return underlines;
        }

        public ArraySet<Integer> getCrosseds() {
            return crosseds;
        }

        public ArraySet<Integer> getUpperRegisters() {
            return upperRegisters;
        }

        public ArraySet<Integer> getLowerRegisters() {
            return lowerRegisters;
        }

        public ArraySet<Integer> getBlurs() {
            return blurs;
        }

        public ArraySet<Integer> getCasuals() {
            return casuals;
        }

        public ArraySet<Integer> getCursives() {
            return cursives;
        }

        public ArraySet<Integer> getMonospaces() {
            return monospaces;
        }

        public HashMap<Integer, Integer> getSizes() {
            return sizes;
        }

        public HashMap<Integer, Integer> getForegroundColors() {
            return foregroundColors;
        }

        public HashMap<Integer, Integer> getBackgroundColors() {
            return backgroundColors;
        }

        public stylesCreateOnTextChangedListener(ArraySet<Integer> bolds, ArraySet<Integer> italics, ArraySet<Integer> underline, ArraySet<Integer> crossed,
                                                 ArraySet<Integer> upperRegister, ArraySet<Integer> lowerRegister, ArraySet<Integer> blurs,
                                                 ArraySet<Integer> casuals, ArraySet<Integer> cursives, ArraySet<Integer> monospaces,
                                                 HashMap<Integer, Integer> sizes, EditText tv1, String last, ColorPickerDialog foregroundColorDialog,
                                                 ColorPickerDialog backgroundColorDialog, HashMap<Integer,
                Integer> foregroundColors, HashMap<Integer, Integer> backgroundColors, String aliment, View pageSettings) {
            this.bolds = bolds;
            this.underlines = underline;
            this.italics = italics;
            this.crosseds = crossed;
            this.upperRegisters = upperRegister;
            this.lowerRegisters = lowerRegister;
            this.blurs = blurs;
            this.casuals = casuals;
            this.cursives = cursives;
            this.monospaces = monospaces;
            this.sizes = sizes;
            this.tv1 = tv1;
            this.last = last;
            this.pageSettings =pageSettings;
            this.foregroundColorDialog = foregroundColorDialog;
            this.backgroundColorDialog = backgroundColorDialog;
            this.foregroundColors = foregroundColors;
            this.backgroundColors = backgroundColors;
            this.aliment = aliment;
        }

        public stylesCreateOnTextChangedListener(EditText tv1, View pageSettings, ColorPickerDialog foregroundColorDialog, String aliment, ColorPickerDialog backgroundColorDialog) {
            this.tv1 = tv1;
            this.pageSettings = pageSettings;
            this.foregroundColorDialog = foregroundColorDialog;
            this.backgroundColorDialog = backgroundColorDialog;
            this.aliment = aliment;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.start = start+before;
        }

        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void afterTextChanged(Editable s) {
            EditText editTextSize =  pageSettings.findViewById(R.id.editTextNumber);
            int textSize = 50;
            if (editTextSize.getText().length()!=0){
                textSize = Integer.parseInt(editTextSize.getText().toString());
            }
            if (textSize>700){
                textSize =50;
            }
            int foreground = foregroundColorDialog.getCurrentColor();
            int background = backgroundColorDialog.getCurrentColor();
            if (foreground==-1){
                foreground=Color.BLACK;
            }
            if (background==-1){
                background=Color.WHITE;
            }
            if (!last.equals(s.toString())) {
                if (last.length()<s.length()){
                    int length = s.length()-last.length();
                    last = s.toString();
                    start--;
                    for (int y = 0; y<length;y++){
                        start = start+1;
                        for (int i = bolds.size()-1;i>=0;i--){
                            if (bolds.valueAt(i)>=start) {
                                bolds.add(bolds.valueAt(i)+1);
                                bolds.removeAt(i);
                            }
                        }
                        for (int i = italics.size()-1;i>=0;i--){
                            if (italics.valueAt(i)>=start) {
                                italics.add(italics.valueAt(i)+1);
                                italics.removeAt(i);
                            }
                        }
                        for (int i = underlines.size()-1; i>=0; i--){
                            if (underlines.valueAt(i)>=start) {
                                underlines.add(underlines.valueAt(i)+1);
                                underlines.removeAt(i);
                            }
                        }
                        for (int i = crosseds.size()-1; i>=0; i--){
                            if (crosseds.valueAt(i)>=start) {
                                crosseds.add(crosseds.valueAt(i)+1);
                                crosseds.removeAt(i);
                            }
                        }
                        for (int i = upperRegisters.size()-1; i>=0; i--){
                            if (upperRegisters.valueAt(i)>=start) {
                                upperRegisters.add(upperRegisters.valueAt(i)+1);
                                upperRegisters.removeAt(i);
                            }
                        }
                        for (int i = lowerRegisters.size()-1; i>=0; i--){
                            if (lowerRegisters.valueAt(i)>=start) {
                                lowerRegisters.add(lowerRegisters.valueAt(i)+1);
                                lowerRegisters.removeAt(i);
                            }
                        }
                        for (int i = blurs.size()-1; i>=0; i--){
                            if (blurs.valueAt(i)>=start) {
                                blurs.add(blurs.valueAt(i)+1);
                                blurs.removeAt(i);
                            }
                        }
                        for (int i = cursives.size()-1;i>=0;i--){
                            if (cursives.valueAt(i)>=start) {
                                cursives.add(cursives.valueAt(i)+1);
                                cursives.removeAt(i);
                            }
                        }
                        for (int i = casuals.size()-1;i>=0;i--){
                            if (casuals.valueAt(i)>=start) {
                                casuals.add(casuals.valueAt(i)+1);
                                casuals.removeAt(i);
                            }
                        }
                        for (int i = monospaces.size()-1; i>=0; i--){
                            if (monospaces.valueAt(i)>=start) {
                                monospaces.add(monospaces.valueAt(i)+1);
                                monospaces.removeAt(i);
                            }
                        }
                        for (int i = sizes.size()-1;i>=0;i--){
                            if (i>=start) {
                                sizes.put(i+1,sizes.get(i));
                                sizes.remove(i);
                            }
                        }
                        for (int i = foregroundColors.size()-1;i>=0;i--){
                            if (i>=start) {
                                foregroundColors.put(i+1,foregroundColors.get(i));
                                foregroundColors.remove(i);
                            }
                        }
                        for (int i = backgroundColors.size()-1;i>=0;i--){
                            if (i>=start) {
                                backgroundColors.put(i+1,backgroundColors.get(i));
                                backgroundColors.remove(i);
                            }
                        }
                        if (bold) {
                            bolds.add(start);
                        }
                        if (italic) {
                            italics.add(start);
                        }
                        if (underline) {
                            underlines.add(start);
                        }
                        if (crossed) {
                            crosseds.add(start);
                        }
                        if (upperRegister) {
                            upperRegisters.add(start);
                        }
                        if (lowerRegister) {
                            lowerRegisters.add(start);
                        }
                        if (blur) {
                            blurs.add(start);
                        }
                        if (casual) {
                            casuals.add(start);
                        }
                        if (cursive) {
                            cursives.add(start);
                        }
                        if (monospace) {
                            monospaces.add(start);
                        }
                        sizes.put(start,textSize);
                        foregroundColors.put(start,foreground);
                        backgroundColors.put(start,background);

                        tv1.removeTextChangedListener(this);
                        s = new SpannableStringBuilder(s.toString());

                        for (int t:bolds) {
                            String se = Html.toHtml(s);
                            s.setSpan(new StyleSpan(Typeface.BOLD), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:italics) {
                            String se = Html.toHtml(s);
                            s.setSpan(new StyleSpan(Typeface.ITALIC), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:underlines) {
                            String se = Html.toHtml(s);
                            s.setSpan(new UnderlineSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:crosseds) {
                            String se = Html.toHtml(s);
                            s.setSpan(new StrikethroughSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:upperRegisters) {
                            String se = Html.toHtml(s);
                            s.setSpan(new SubscriptSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:lowerRegisters) {
                            String se = Html.toHtml(s);
                            s.setSpan(new SuperscriptSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:blurs) {
                            String se = Html.toHtml(s);
                            s.setSpan(new MaskFilterSpan(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL)), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:casuals) {
                            String se = Html.toHtml(s);
                            s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:cursives) {
                            String se = Html.toHtml(s);
                            s.setSpan(new TypefaceSpan("cursive"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        for (int t:monospaces) {
                            String se = Html.toHtml(s);
                            s.setSpan(new TypefaceSpan("monospace"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                        Editable finalS = s;
                        sizes.forEach((x, z)->{
                            if (z==null) z = 50;
                            finalS.setSpan(new AbsoluteSizeSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        });
                        foregroundColors.forEach((x, z)->{
                            if (z==null)z = Color.BLACK;
                            finalS.setSpan(new ForegroundColorSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        });
                        backgroundColors.forEach((x, z)->{
                            if (z==null) z=Color.WHITE;
                            finalS.setSpan(new BackgroundColorSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        });

                        if (aliment.equals("right")) s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_RIGHT), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        else if (aliment.equals("center")) s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                        else s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_LEFT), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                        tv1.setText(s);
                        tv1.setSelection(start+1);
                        tv1.addTextChangedListener(this);
                    }
                }
                else {
                    int length = last.length()-s.length();
                    last = s.toString();
                    start++;
                    int startConst = start;
                    for (int y = 0; y<length;y++){
                        start = start-1;
                        bolds.remove(start-1);
                        italics.remove(start-1);
                        underlines.remove(start-1);
                        crosseds.remove(start-1);
                        upperRegisters.remove(start-1);
                        lowerRegisters.remove(start-1);
                        blurs.remove(start-1);
                        casuals.remove(start-1);
                        cursives.remove(start-1);
                        monospaces.remove(start-1);
                        sizes.remove(start-1);
                        foregroundColors.remove(start-1);
                        backgroundColors.remove(start-1);
                    }
                    start = startConst;
                    for (int y = 0; y<length;y++) {
                        start = start-1;
                        for (int i = 0;i<bolds.size();i++){
                            if (bolds.valueAt(i)>=start) {
                                bolds.add(bolds.valueAt(i)-1);
                                bolds.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<italics.size();i++){
                            if (italics.valueAt(i)>=start) {
                                italics.add(italics.valueAt(i)-1);
                                italics.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<underlines.size();i++){
                            if (underlines.valueAt(i)>=start) {
                                underlines.add(underlines.valueAt(i)-1);
                                underlines.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<crosseds.size();i++){
                            if (crosseds.valueAt(i)>=start) {
                                crosseds.add(crosseds.valueAt(i)-1);
                                crosseds.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<upperRegisters.size();i++){
                            if (upperRegisters.valueAt(i)>=start) {
                                upperRegisters.add(upperRegisters.valueAt(i)-1);
                                upperRegisters.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<lowerRegisters.size();i++){
                            if (lowerRegisters.valueAt(i)>=start) {
                                lowerRegisters.add(lowerRegisters.valueAt(i)-1);
                                lowerRegisters.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<blurs.size();i++){
                            if (blurs.valueAt(i)>=start) {
                                blurs.add(blurs.valueAt(i)-1);
                                blurs.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<casuals.size();i++){
                            if (casuals.valueAt(i)>=start) {
                                casuals.add(casuals.valueAt(i)-1);
                                casuals.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<cursives.size();i++){
                            if (cursives.valueAt(i)>=start) {
                                cursives.add(cursives.valueAt(i)-1);
                                cursives.removeAt(i+1);
                            }
                        }
                        for (int i = 0;i<monospaces.size();i++){
                            if (monospaces.valueAt(i)>=start) {
                                monospaces.add(monospaces.valueAt(i)-1);
                                monospaces.removeAt(i+1);
                            }
                        }
                        for (int i = 0; i<=sizes.size();i++){
                            if (i>=start){
                                sizes.put(i-1,sizes.get(i));
                                sizes.remove(i);
                            }
                        }
                        for (int i = 0; i<=foregroundColors.size();i++){
                            if (i>=start){
                                foregroundColors.put(i-1,foregroundColors.get(i));
                                foregroundColors.remove(i);
                            }
                        }
                        for (int i = 0; i<=backgroundColors.size();i++){
                            if (i>=start){
                                backgroundColors.put(i-1,backgroundColors.get(i));
                                backgroundColors.remove(i);
                            }
                        }
                    }
                    tv1.removeTextChangedListener(this);
                    s = new SpannableStringBuilder(s.toString());
                    for (int t:bolds) {
                        String se = Html.toHtml(s);
                        s.setSpan(new StyleSpan(Typeface.BOLD), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:italics) {
                        String se = Html.toHtml(s);
                        s.setSpan(new StyleSpan(Typeface.ITALIC), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:underlines) {
                        String se = Html.toHtml(s);
                        s.setSpan(new UnderlineSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:crosseds) {
                        String se = Html.toHtml(s);
                        s.setSpan(new StrikethroughSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:upperRegisters) {
                        String se = Html.toHtml(s);
                        s.setSpan(new SubscriptSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:lowerRegisters) {
                        String se = Html.toHtml(s);
                        s.setSpan(new SuperscriptSpan(), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:blurs) {
                        String se = Html.toHtml(s);
                        s.setSpan(new MaskFilterSpan(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL)), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:casuals) {
                        String se = Html.toHtml(s);
                     /*       if (bolds.contains(t)&&italics.contains(t)){
                                s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }else if (bolds.contains(t)){
                                s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }else if (italics.contains(t)){
                                s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            } else {
                                s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }*/
                        s.setSpan(new TypefaceSpan("serif"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:cursives) {
                        String se = Html.toHtml(s);
                        s.setSpan(new TypefaceSpan("cursive"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    for (int t:monospaces) {
                        String se = Html.toHtml(s);
                        s.setSpan(new TypefaceSpan("monospace"), t, t + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    Editable finalS1 = s;
                    sizes.forEach((x, z)->{
                        if (z==null)z = 50;
                        finalS1.setSpan(new AbsoluteSizeSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    });
                    foregroundColors.forEach((x, z)->{
                        if (z==null)z = Color.BLACK;
                        finalS1.setSpan(new ForegroundColorSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    });
                    backgroundColors.forEach((x, z)->{
                        if (z==null)z= Color.WHITE;
                        finalS1.setSpan(new BackgroundColorSpan(z), x, x + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    });
                    if (aliment.equals("right")) s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_RIGHT), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    else if (aliment.equals("center")) s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    else s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_LEFT), 0, s.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                    tv1.setText(s);
                    tv1.setSelection(start-1);
                    tv1.addTextChangedListener(this);
                }
            }
        }
    }
}

