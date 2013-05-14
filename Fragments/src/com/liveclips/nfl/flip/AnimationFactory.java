package com.liveclips.nfl.flip;



import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

public class AnimationFactory {

	public static enum FlipDirection {
		LEFT_RIGHT, 
		RIGHT_LEFT;
		
		public float getStartDegreeForFirstView() {
			return 0;
		}
		
		public float getStartDegreeForSecondView() {
			switch(this) {
			case LEFT_RIGHT:
				return -90;
			case RIGHT_LEFT:
				return 90;
			default:
				return 0;
			}
		}
		
		public float getEndDegreeForFirstView() {
			switch(this) {
			case LEFT_RIGHT:
				return 90;
			case RIGHT_LEFT:
				return -90;
			default:
				return 0;
			}
		}
		
		public float getEndDegreeForSecondView() {
			return 0;
		}
		
		public FlipDirection theOtherDirection() {
			switch(this) {
			case LEFT_RIGHT:
				return RIGHT_LEFT;
			case RIGHT_LEFT:
				return LEFT_RIGHT;
			default:
				return null;
			}
		}
	}

		
	/**
	 * Create a pair of {@link FlipAnimation} that can be used to flip 3D transition from {@code fromView} to {@code toView}. A typical use case is with {@link ViewAnimator} as an out and in transition.
	 * 
	 * NOTE: Avoid using this method. Instead, use {@link #flipTransition}.
	 *  
	 * @param fromView the view transition away from
	 * @param toView the view transition to
	 * @param dir the flip direction
	 * @param duration the transition duration in milliseconds
	 * @param interpolator the interpolator to use (pass {@code null} to use the {@link AccelerateInterpolator} interpolator) 
	 * @return
	 */
	public static Animation[] flipAnimation(final View fromView, final View toView, FlipDirection dir, long duration, Interpolator interpolator) {
		Animation[] result = new Animation[2];
		float centerX;
		float centerY;
		
		centerX = fromView.getWidth() / 2.0f;
		centerY = fromView.getHeight() / 2.0f; 
		
		Animation outFlip= new FlipAnimation(dir.getStartDegreeForFirstView(), dir.getEndDegreeForFirstView(), centerX, centerY, FlipAnimation.SCALE_DEFAULT, FlipAnimation.ScaleUpDownEnum.SCALE_DOWN);
		outFlip.setDuration(duration);
		outFlip.setFillAfter(true);
		outFlip.setInterpolator((android.view.animation.Interpolator) (interpolator==null?new AccelerateInterpolator():interpolator)); 

		AnimationSet outAnimation = new AnimationSet(true);
		outAnimation.addAnimation(outFlip); 
		result[0] = outAnimation; 
		
		// Uncomment the following if toView has its layout established (not the case if using ViewFlipper and on first show)
		//centerX = toView.getWidth() / 2.0f;
		//centerY = toView.getHeight() / 2.0f; 
		
		Animation inFlip = new FlipAnimation(dir.getStartDegreeForSecondView(), dir.getEndDegreeForSecondView(), centerX, centerY, FlipAnimation.SCALE_DEFAULT, FlipAnimation.ScaleUpDownEnum.SCALE_UP);
		inFlip.setDuration(duration);
		inFlip.setFillAfter(true);
		inFlip.setInterpolator((android.view.animation.Interpolator) (interpolator==null?new AccelerateInterpolator():interpolator));
		inFlip.setStartOffset(duration);   
		
		AnimationSet inAnimation = new AnimationSet(true); 
		inAnimation.addAnimation(inFlip); 
		result[1] = inAnimation; 
		
		return result;
		
	}
	/**
	 * Flip to the next view of the {@code ViewAnimator}'s subviews. A call to this method will initiate a {@link FlipAnimation} to show the next View.  
	 * If the currently visible view is the last view, flip direction will be reversed for this transition.
	 *  
	 * @param playCardViewAnimator the {@code ViewAnimator}
	 * @param dir the direction of flip
	 */
		public static void flipTransition(final ViewAnimator viewAnimator, FlipDirection dir,RelativeLayout parentLayout,RelativeLayout layoutToAdd,RelativeLayout layoutToRemove) {   
			
			final View fromView = viewAnimator.getCurrentView();
			final View toView = viewAnimator.getCurrentView();

			Animation[] animc = AnimationFactory.flipAnimation(fromView, toView, dir, 2000, null);
	  
			
			parentLayout.removeView(layoutToRemove);
			parentLayout.addView(layoutToAdd);
			viewAnimator.setOutAnimation(animc[0]);
			viewAnimator.setInAnimation(animc[1]);
			viewAnimator.showNext();
		
			  
		}
	
}
