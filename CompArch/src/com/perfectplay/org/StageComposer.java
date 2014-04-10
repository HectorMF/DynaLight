package com.perfectplay.org;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/* 
 * A simple static method to create a stage.
 * Written By: Hector Medina-Fetterman
 * Date: 12/7/2013
 */
public class StageComposer {
	public static Window window;
	public static Skin skin;
	public static void compose(Stage stage, Skin skin){
		Table table = new Table(skin);
		window = new Window("Controls",skin);

		StageComposer.skin = skin;
		table.setFillParent(true);
		table.align(Align.left + Align.top);
		String[] treeList = new String[3];
		treeList[0] = ("H Tree");
		treeList[1] = ("Pythagoras Tree");
		treeList[2] = ("Fern");
		SelectBox box = new SelectBox(treeList, skin);
		Table tempTable = new Table(skin);
		tempTable.row();
		tempTable.add("Type: ").padLeft(10).padRight(14);
		box.addListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			System.out.println();
			StageComposer.window.getChildren().get(2).remove();

		}});
		
		tempTable.add(box);
		tempTable.add(new Label("",skin)).padLeft(10).width(80);

		tempTable.padTop(10);
		window.add(tempTable);
		window.row();
		
		table.padLeft(10);
		table.padTop(10);
		table.add(window);
		stage.addActor(table);
	}

}