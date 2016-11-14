import eventgen.base.*
import eventgen.process.*
import org.jlab.groot.data.*
import javax.swing.JFrame
import org.jlab.groot.graphics.EmbeddedCanvas


double beamEnergy = 5.498
double thetaMin = 5.0
double thetaMax = 90.0
double finalEnergyMin = 1.0
double finalEnergyMax = 5.0

int numberOfEvents = 100000
InverseSquareProcess process = new InverseSquareProcess(beamEnergy, thetaMin, thetaMax, finalEnergyMin, finalEnergyMax)
process.setValue(1)

print "Process initialized..."

SingleProcessGenerator generator = new SingleProcessGenerator()
generator.setProcess(process)
generator.generate(numberOfEvents)

H1F px = generator.getHistogram("px")
H1F py = generator.getHistogram("py")
H1F energy = generator.getHistogram("energy")
H2F pxpy = generator.getHistogram("px","py")

px.setTitleX("Px (GeV/c)")
px.setFillColor(32)

py.setTitleX("Py (GeV/c)")
py.setFillColor(33)

energy.setTitleX("E' (GeV)")
energy.setFillColor(34)

JFrame frame = new JFrame("Title of the Frame")
EmbeddedCanvas canvas = new EmbeddedCanvas()
frame.setSize(1200,1000)

canvas.divide(2,2)
canvas.cd(0)
canvas.draw(px)
canvas.cd(1)
canvas.draw(py)
canvas.cd(2)
canvas.draw(energy)
canvas.cd(3)
canvas.draw(pxpy)

frame.add(canvas)
frame.setLocationRelativeTo(null)
frame.setVisible(true)

// Big File - Use Care 
//generator.saveEvents("inverseSquareEvents.lund")