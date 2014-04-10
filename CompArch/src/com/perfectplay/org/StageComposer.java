package com.perfectplay.org;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/* 
 * A simple static method to create a stage.
 * Written By: Hector Medina-Fetterman
 * Date: 12/7/2013
 */
public class StageComposer {
	public static Window window;
	public static Skin skin;
	public static Label sliderVal;
	public static Slider slider;
	
	public static void compose(Stage stage, Skin skin){
		Table table = new Table(skin);
		window = new Window("Controls",skin);

		StageComposer.skin = skin;
		table.setFillParent(true);
		table.align(Align.left + Align.top);
		Table tempTable = new Table(skin);
		tempTable.row();
		tempTable.add("Sun Lumens: ").padLeft(10).padRight(14);
		slider = new Slider(0, 10000, 100, false, skin);
		sliderVal = new Label(slider.getValue() +"",skin);
		slider.addListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			sliderVal.setText(slider.getValue() + "");
			Sun.instance.setLumens((int) slider.getValue());
		}});
		
		tempTable.add(slider);
		tempTable.add(sliderVal).padLeft(10).width(80);
		TextButton step = new TextButton("Step", skin);
		step.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				SensorController.instance.update();
			}
		});
		tempTable.row().expandX().bottom();
		tempTable.add(step).width(80).height(30).pad(0).padTop(20).padBottom(20).padRight(0);
		TextButton run = new TextButton("Run", skin);
		run.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				DynaLight.isRunning = true;
			}
		});
		tempTable.add(run).width(80).height(30).pad(0).padTop(20).padBottom(20).padLeft(-20);
		
		TextButton stop = new TextButton("Stop", skin);
		stop.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				DynaLight.isRunning = false;
			}
		});
		tempTable.add(stop).width(80).height(30).pad(0).padTop(20).padBottom(20).padRight(20);
		tempTable.padTop(20);
		
		
		window.add(tempTable);
		window.row();
		
		table.padLeft(25);
		table.padTop(45);
		table.add(window);
		stage.addActor(table);
	}

}