package evl.rlt;


import lombok.Data;
import evl.common.AttributesImpl;

/**
 * @author  Liuyisi
 * @since   0.1
 */
@Data
public class SimpleResult extends AttributesImpl implements Result {

    private double score;

    private int weight;

    private boolean valid = true;

    private String description;

    @Override
    public double score() {
        return score;
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean valid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "SimpleResult{" +
                "score=" + score +
                ", weight=" + weight +
                ", valid=" + valid +
                ", description='" + description + '\'' +
                ", attributes=" + super.toString() + // 调用 AttributesImpl 的 toString
                '}';
    }
}
