package iu7.model

class Graphic {
    def x
    def y
    def alpha

    Graphic() {
        x = []
        y = []
        alpha = 0
    }

    def getAlpha() {
        return alpha
    }

    void setAlpha(alpha) {
        this.alpha = alpha
    }

    def getX(int index) {
        return x[index]
    }

    void addX(value) {
        this.x.add(value)
    }

    def getY(int index) {
        return y[index]
    }

    void addY(value) {
        this.y.add(value)
    }

}
