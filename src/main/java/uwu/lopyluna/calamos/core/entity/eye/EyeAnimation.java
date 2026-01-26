package uwu.lopyluna.calamos.core.entity.eye;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Made with Blockbench 4.9.4
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author lopyluna
 */
public class EyeAnimation {
	public static final AnimationDefinition axis_x = AnimationDefinition.Builder.withLength(38.4167F).looping()
		.addAnimation("north", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -720.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 720.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("south", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 720.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -720.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("axis_x", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, -1440.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition axis_z = AnimationDefinition.Builder.withLength(24.0F).looping()
		.addAnimation("east", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(720.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(-720.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("west", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(-720.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(720.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("axis_z", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(8.0F, KeyframeAnimations.degreeVec(0.0F, 1440.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(16.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(24.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition axis_y = AnimationDefinition.Builder.withLength(12.0F).looping()
		.addAnimation("axis_y", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(360.0F, -360.0F, -360.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(6.0F, KeyframeAnimations.degreeVec(-360.0F, 360.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(9.0F, KeyframeAnimations.degreeVec(360.0F, -360.0F, 360.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(12.0F, KeyframeAnimations.degreeVec(-360.0F, 0.0F, -360.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}
