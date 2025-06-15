import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.system.exitProcess

fun main(args: Array<String> ) {
    var triangle1 = Triangle(
        Point(0, 0),
        Point(1, 1),
        Point(2, 1))

    println(triangle1.perimeter())
    println(triangle1.square())
    triangle1.print()
    triangle1.C(Point(2, 2))
    triangle1.print()
    triangle1.A(Point(2, 2))
    triangle1.print()

    triangle1.rotate(45)
    triangle1.print()
}

class Point(private var X: Double, private var Y: Double) {
    constructor(X_: Int, Y_: Int) : this(X_.toDouble(), Y_.toDouble())

    fun X(): Double { return this.X }
    fun Y(): Double { return this.Y }

    fun X( Value: Double ) { this.X = Value }
    fun Y( Value: Double ) { this.Y = Value }

    fun Y(Value: Int) { this.Y = Value.toDouble() }
    fun X(Value: Int) { this.X = Value.toDouble() }

    fun isCollinearity(A: Point, B: Point) : Boolean {
        return ((B.X - A.X) * (Y - A.Y) - (B.Y - A.Y) * (X - A.X)) == 0.0
    }

    fun rotate(angle_degrees: Double, center : Point) {
        val angle_rad = Math.toRadians(angle_degrees)

        val dx = X - center.X
        val dy = Y - center.Y

        X =  dx * cos(angle_rad) - dy * sin(angle_rad) + center.X
        Y =  dx * sin(angle_rad) + dy * cos(angle_rad) + center.Y
    }
    fun rotate(angle_degrees: Number, center : Point) { rotate(angle_degrees.toDouble(), center) }
}

fun Len( A:Point, B:Point ): Double {
    return sqrt((A.X() - B.X()) * (A.X() - B.X()) + (A.Y() - B.Y()) * (A.Y() - B.Y()))
}

class Triangle(private var A: Point, private var B: Point, private var C: Point) {

    init {
        if (C.isCollinearity(A ,B)) {
            println("Triangle can`t be a line")
            exitProcess(1)
        }
    }
    fun print() {
        println("Triangle points: A-> (${A.X()}, ${A.Y()}) B->(${B.X()}, ${B.Y()}) C->(${C.X()}, ${C.Y()}), " +
                "perimeter -> ${perimeter()} and square -> ${square()}")
    }

    fun perimeter(): Double {
        return Len(A, B) + Len(B, C) + Len(A, C)
    }

    fun square(): Double {
        val p: Double = this.perimeter() / 2

        return sqrt(p*(p-Len(A, B))*(p-Len(B, C))*(p-Len(A, C)))
    }

    fun A(P : Point) {
        if (P.isCollinearity(B, C)) {
            println("Triangle can`t be a line")
            return
        }
        this.A = P
    }

    fun B(P : Point) {
        if (P.isCollinearity(A, C)) {
            println("Triangle can`t be a line")
            return
        }
        this.B = P
    }

    fun C(P : Point) {
        if (P.isCollinearity(A, B)) {
            println("Triangle can`t be a line")
            return
        }
        this.C = P
    }

    fun rotate(angle_degrees: Double) {
        val cx = (A.X() + B.X() + C.X()) / 3
        val cy = (A.Y() + B.Y() + C.Y()) / 3

        val center = Point(cx, cy)

        A.rotate(angle_degrees, center)
        B.rotate(angle_degrees, center)
        C.rotate(angle_degrees, center)
    }
    fun rotate(angle_degrees: Number) {rotate(angle_degrees.toDouble())}
}