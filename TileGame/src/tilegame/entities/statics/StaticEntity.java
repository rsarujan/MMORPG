package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.entities.Entity;

//Entites immobiles ex: arbre, rocher, maison
public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

}
