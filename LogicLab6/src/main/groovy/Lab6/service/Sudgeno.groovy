package Lab6.service

interface Sudgeno {

    void readVars(def json_data)

    void readFunction(def json_data)

    void readRule(def json_data)

    double calculate(def h, def d, def result_sname)

    def getXFromJson(def json_data, def sname)

    def getFuzzyForVar(def json_data)

    def findMin(def fuzzy_array)

    def calcResultArray(def mins, def result_sname)

    def calcResult(def maxs, def result_sname)

    void getGraphicsForVar(def sname, def startX, def endX, def stepX)

    def getGraphicsForVar()

    def getGraphicsForRule()

    def getGraphicsTotal()

}