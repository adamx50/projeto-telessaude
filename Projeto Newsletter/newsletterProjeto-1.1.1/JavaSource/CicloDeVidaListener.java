import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


public class CicloDeVidaListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4230680342105017227L;

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}
	

	@Override
	public void beforePhase(PhaseEvent event) {
		
		System.out.println("Início de fase!" + event.getPhaseId());
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		
		System.out.println("Fim de fase!" + event.getPhaseId());
	}


}
