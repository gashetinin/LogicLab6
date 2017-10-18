package Lab6.model

class FunctionSudgeno {
    def id
    def ka
    def kb
    def comment

    FunctionSudgeno(def json_data) {
        this.id = json_data.id
        this.ka = json_data.ka
        this.kb = json_data.kb
        this.comment = json_data.comment
    }

    def getId() {
        return id
    }

    void setId(id) {
        this.id = id
    }

    def getComment() {
        return comment
    }

    void setComment(comment) {
        this.comment = comment
    }

    double calculateValue(double x) {
        return ka*x + kb
    }

    double calculateValueWithMax(double x, double max) {
        def y = calculateValue(x)
        return y > max ? max : y
    }

    double calculateLine(double x, double y) {
        return ka*x + kb*y
    }

    Graphic getArray(double startX, double endX, double stepX) {
        Graphic graphic = new Graphic()

        double x0 = startX
        while (x0 < endX + stepX/2) {
            graphic.addX(x0)
            graphic.addY(calculateValue(x0))
        }
        return graphic

    }

}
