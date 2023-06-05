package com.example.k_g;

public class Placement_Stats_Uploadmodel {
    private String avg_ctc;
    private String highest_ctc;
    private String total_companies;
    private String total_offers;
    private String total_students_placed;





    public Placement_Stats_Uploadmodel()
    {

    }

    public Placement_Stats_Uploadmodel(String avg_ctc, String highest_ctc, String total_companies, String total_offers, String total_students_placed)
    {
        this.avg_ctc=avg_ctc;
        this.highest_ctc=highest_ctc;
        this.total_companies=total_companies;
        this.total_offers=total_offers;
        this.total_students_placed=total_students_placed;
    }

    public String getAvg_ctc() {
        return avg_ctc;
    }

    public void setAvg_ctc(String avg_ctc) {
        this.avg_ctc = avg_ctc;
    }

    public String getHighest_ctc() {
        return highest_ctc;
    }

    public void setHighest_ctc(String highest_ctc) {
        this.highest_ctc = highest_ctc;
    }

    public String getTotal_companies() {
        return total_companies;
    }

    public void setTotal_companies(String total_companies) {
        this.total_companies = total_companies;
    }

    public String getTotal_offers() {
        return total_offers;
    }

    public void setTotal_offers(String total_offers) {
        this.total_offers = total_offers;
    }

    public String getTotal_students_placed() {
        return total_students_placed;
    }

    public void setTotal_students_placed(String total_students_placed) {
        this.total_students_placed = total_students_placed;
    }
}
