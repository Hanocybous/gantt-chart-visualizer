package com.hanocybous.reporter;

public interface IReporter {

    /**
     * @return an integer representing the success of the operation (0 = success, 1 = failure)
     */
    public int makeReportTXT();
    /**
     * @return an integer representing the success of the operation (0 = success, 1 = failure)
     */
    public int makeReportHTML();
    /**
     * @return an integer representing the success of the operation (0 = success, 1 = failure)
     */
    public int makeReportMD();
    /**
     * @return an integer representing the success of the operation (0 = success, 1 = failure)
     */
    public int createReport();

}
