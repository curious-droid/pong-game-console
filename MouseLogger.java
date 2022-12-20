import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

public class MouseLogger implements NativeMouseInputListener {
	public void nativeMouseClicked(NativeMouseEvent e) {
		GameRunner.handleClick();
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		GameRunner.setMouseCoords(e.getX(), e.getY());
	}
}