import eventgen.base.*
import eventgen.process.*
import org.jlab.groot.data.*
import javax.swing.JFrame
import org.jlab.groot.graphics.EmbeddedCanvas


double beamEnergy = 11.0
double thetaMin = 18.0
double thetaMax = 60.0
double finalEnergyMin = 1.2
double finalEnergyMax = 10.8

int numberOfEvents = 100000
UniformProcess uniform = new UniformProcess(beamEnergy, thetaMin, thetaMax, finalEnergyMin, finalEnergyMax)
uniform.setValue(0.1)

print "Process initialized..."

SingleProcessGenerator generator = new SingleProcessGenerator()
generator.setProcess(uniform)
generator.generate(numberOfEvents)

//for (int i=0; i<50; i++)
//    print generator.getEvent(i).toString()

H1F px = generator.getHistogram(11,"px",100,-5.0,5.0)
H1F py = generator.getHistogram(11,"py",100,-5.0,5.0)
H1F energy = generator.getHistogram(11,"energy",100,0.0,11.0)
H2F pxpy = generator.getHistogram(11,"px","py",50,-5.0,5.0,50,-5.0,5.0)
H2F xQQ = generator.getHistogram("x","qq",50,0.0,1.0,50,-0.1,5.0)
H2F wQQ = generator.getHistogram("w","qq",50,0.0,3.5,50,-0.1,5.0)

H1F[] pars = generator.getParameterHistograms(0)
H1F xBjorken = generator.getHistogram("x",100,-0.5,1.5)

pars[0].setTitleX("par0")
pars[0].setFillColor(35)

xBjorken.setTitleX("x")
xBjorken.setFillColor(32)

px.setTitleX("Px (GeV/c)")
px.setFillColor(32)

py.setTitleX("Py (GeV/c)")
py.setFillColor(33)

energy.setTitleX("E' (GeV)")
energy.setFillColor(34)

JFrame frame = new JFrame("Title of the Frame")
EmbeddedCanvas canvas = new EmbeddedCanvas()
frame.setSize(1200,1000)

canvas.divide(3,3)
canvas.cd(0)
canvas.draw(xBjorken)
canvas.cd(1)
canvas.draw(px)
canvas.cd(2)
canvas.draw(py)
canvas.cd(3)
canvas.draw(energy)
canvas.cd(4)
canvas.draw(pars[0])
canvas.cd(5)
canvas.draw(pars[1])
canvas.cd(6)
canvas.draw(pxpy)
canvas.cd(7)
canvas.draw(xQQ)
canvas.cd(8)
canvas.draw(wQQ)

frame.add(canvas)
frame.setLocationRelativeTo(null)
frame.setVisible(true)

// Big file use care 
// generator.saveEvents("uniformEvents.lund")