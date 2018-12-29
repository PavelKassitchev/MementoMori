package com.example.pavka.memento;

public enum Questions {
    Q1("Вы курите, или жуете табак, или постоянно торчите там, где курят?", -2.0, 2.0),
    Q2("Вы съедаете в неделю больше, чем пару сосисок в тесте, кусочков копченого сала или жареных пончиков?", -0.6, 0.6),
    Q3("Вы жарите рыбу, курицу, мясо почти до черноты?", -0.4, 0.4),
    Q4("Вы избегаете в еде сливочного масла, жирной сметаны, шоколада, жареного?", 2.0, -2.0),
    Q5("Вы стараетесь есть меньше мяса, отдавая предпочтение овощам?", 1.8, -1.8),
    Q6("Вы выпиваете пива больше двух бутылок по 0,33 л в день или вина больше 300 г в день или водки больше 100 г в день?", -1.2, 0.6),
    Q7("Вы живете в загрязненной атмосфере?", -1.0, 1.0),
    Q8("Вы выпиваете больше 450 г крепкого кофе в день?", -0.6, 0.6),
    Q9("Вы принимаете аспирин?", 0.8, -0.8),
    Q10("Вы чистите зубы специальной ниткой каждый день?", 1.2, -1.2),
    Q11("Вы имеете стул реже, чем один раз в два дня?", -0.8, 0.8),
    Q12("Вы вступаете в рискованные половые связи или балуетесь наркотиками?", -1.6, 1.6),
    Q13("Вы стараетесь загореть?", -1.4, 1.4),
    Q14("В вашем доме повышенный уровень радона?", 1.4, -0.05),
    Q15("Ваш вес в норме?", 1.8, -1.8),
    Q16("У вас есть жена (муж)?", 1.8, -1.8),
    Q17("Вы умеете бороться со стрессом?", 1.4, -1.4),
    Q18("Ваши кровные родственники страдают диабетом (больше одного)?", -0.9, 0.9),
    Q19("Кто-то из ваших родителей умер от болезни до достижения ими 75 лет?", -2.0, 2.0),
    Q20("Члены вашей семьи (дедушки-бабушки, их сестры-братья, дяди-тети) доживали до 90 лет (больше одного)?", 4.9, -4.9),
    Q21("Вы лежебока, который не занимается спортом?", -1.5, 1.5),
    Q22("Вы принимаете витамин Е?", 1.5, -1.5);


    private final String QUESTION;
    private final double IF_Positive;
    private final double IF_Negative;

    Questions(String s, double p, double n)
    {
        QUESTION = s;
        IF_Positive = p;
        IF_Negative = n;
    }
    @Override
    public String toString()
    {
        return QUESTION;
    }
    public static String[] getStringArray()
    {
        int len = Questions.values().length;
        String [] qs = new String [len];
        for (int i=0; i<len; i++) qs[i] = Questions.values()[i].toString();
        return qs;
    }
}
