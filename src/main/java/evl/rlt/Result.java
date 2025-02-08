package evl.rlt;


import evl.common.Attributes;

public interface Result extends Attributes {

    double score();

    void setScore(double score);

    int weight();

    void setWeight(int weight);

    boolean valid();

    void setValid(boolean valid);

    String description();

    void setDescription(String description);
}
