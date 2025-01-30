import java.util.Objects;

/**
 * YearlyWaterRecord class is a record used to hold country water data;
 */
public record YearlyWaterRecord(String isoYear, double basicPlusPct, double limitedPct, double unimprovedPct, double surfacePct) implements Comparable<YearlyWaterRecord> {
    @Override
    public int compareTo(YearlyWaterRecord o) {
        if(o == null){
            throw new IllegalArgumentException("o must not be null");
        }
        if (this.equals(o)){
            return 0;
        }
        else{
            return this.isoYear.compareTo(o.isoYear);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass()){
            throw new IllegalArgumentException("obj must be the same class as "+this.getClass());
        }
        return (Objects.equals(this.isoYear, ((YearlyWaterRecord) obj).isoYear));
    }

    @Override
    public String toString() {
        return "ISO Year: " + isoYear + "\n" + "BasicPlusPCT: " + basicPlusPct + "\n" + "LimitedPCT: " + limitedPct + "\n"+ "UnimprovedPCT: " + unimprovedPct + "\n" + "SurfacePCT: " + surfacePct;
    }
}
