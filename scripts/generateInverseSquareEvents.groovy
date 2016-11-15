import eventgen.base.*
import eventgen.process.*
import org.jlab.groot.data.*
import javax.swing.JFrame
import org.jlab.groot.graphics.EmbeddedCanvas


double beamEnergy = 8.25
double thetaMin = 15.0
double thetaMax = 60.0
double finalEnergyMin = 1.0
double finalEnergyMax = 7.9

int numberOfEvents = 1000000
InverseSquareProcess process = new InverseSquareProcess(beamEnergy, thetaMin, thetaMax, finalEnergyMin, finalEnergyMax)
process.setValue(1)

print "Process initialized... \n"

SingleProcessGenerator generator = new SingleProcessGenerator()
generator.setProcess(process)
generator.generate(numberOfEvents)

H1F px = generator.getHistogram(11,"px",100,-3,3)
H1F py = generator.getHistogram(11,"py",100,-3,3)
H1F energy = generator.getHistogram(11,"energy",100,0,10)
H1F x = generator.getHistogram("x",100,-0.05, 1.5)
H1F w = generator.getHistogram("w",100,-0.05, 3.5)
H1F qq = generator.getHistogram("qq",100,-0.05, 4.5)
H2F pxpy = generator.getHistogram(11,"px","py",100,-3,3,100,-3,3)
H2F xQQ = generator.getHistogram("x","qq",100,0,0.4,100,0,2)
H2F wQQ = generator.getHistogram("w","qq",100,0,6,100,0,2)

px.setTitleX("Px (GeV/c)")
px.setFillColor(32)

py.setTitleX("Py (GeV/c)")
py.setFillColor(33)

energy.setTitleX("E' (GeV)")
energy.setFillColor(34)

x.setTitleX("x_Bjorken")
x.setFillColor(34)
w.setTitleX("W (GeV/c^{2})")
w.setFillColor(35)
qq.setTitleX("Q^{2} (GeV^2/c^2) ")
qq.setFillColor(36)

JFrame frame = new JFrame("Title of the Frame")
EmbeddedCanvas canvas = new EmbeddedCanvas()
frame.setSize(1200,1000)

canvas.divide(3,3)
canvas.cd(0)
canvas.draw(px)
canvas.cd(1)
canvas.draw(py)
canvas.cd(2)
canvas.draw(energy)
canvas.cd(3)
canvas.draw(x)
canvas.cd(4)
canvas.draw(w)
canvas.cd(5)
canvas.draw(qq)
canvas.cd(6)
canvas.draw(pxpy)
canvas.cd(7)
canvas.draw(xQQ)
canvas.cd(8)
canvas.draw(wQQ)

frame.add(canvas)
frame.setLocationRelativeTo(null)
frame.setVisible(true)

// Big File - Use Care 
//generator.saveEvents("inverseSquareEvents.lund")