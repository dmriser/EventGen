import eventgen.base.*

Dimension x = new Dimension("x", 0.0, 1.0)
Dimension y = new Dimension("y", 0.0, 1.0)
Dimension q2 = new Dimension("q2", 1.0, 6.0)

PhaseSpace phaseSpace = new PhaseSpace("test phase space")
phaseSpace.addDimension(x)
phaseSpace.addDimension(y)
phaseSpace.addDimension(q2)

int numberOfTrials = 100
double[] point = new double[phaseSpace.getDimensionality()]

for (int trial=0; trial<numberOfTrials; trial++){
    point = phaseSpace.getRandom()
    print "trial=${trial}, x=${point[0]}, y=${point[1]}, q2=${point[2]} \n"
}
