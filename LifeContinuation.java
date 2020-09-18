/**
 * Utilizing Java's project loom to describe current state of our life. 
 */
// cerner_2^5_2020
public class LifeContinuation {
	public static void liveLife(ContinuationScope scope) {
		System.out.println("Get up ");
		Continuation.yield(scope);
		System.out.println("Eat");
		Continuation.yield(scope);
		System.out.println("Work");
		Continuation.yield(scope);
		System.out.println("Play");
		Continuation.yield(scope);
		System.out.println("Sleep");
	}

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("!!!!!! CAUGHT COVID !!!!!");
			}
		});
		while (true) {
			var scope = new ContinuationScope("Life");
			var continuation = new Continuation(scope, () -> liveLife(scope));
			while (!continuation.isDone()) {
				System.out.println("Breathe!");
				continuation.run();
			}
		}
	}
}
