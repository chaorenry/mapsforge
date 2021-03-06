/*
 * Copyright 2010, 2011, 2012, 2013 mapsforge.org
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.map.rendertheme.renderinstruction;

import java.util.List;

import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.Tag;
import org.mapsforge.map.rendertheme.RenderCallback;

/**
 * Represents a text label on the map.
 */
public class Caption implements RenderInstruction {
	private final float dy;
	private final Paint fill;
	private final float fontSize;
	private final Paint stroke;
	private final TextKey textKey;

	Caption(CaptionBuilder captionBuilder) {
		this.dy = captionBuilder.dy;
		this.fill = captionBuilder.fill;
		this.fontSize = captionBuilder.fontSize;
		this.stroke = captionBuilder.stroke;
		this.textKey = captionBuilder.textKey;
	}

	@Override
	public void destroy() {
		// no-op
	}

	@Override
	public void renderNode(RenderCallback renderCallback, List<Tag> tags) {
		String caption = this.textKey.getValue(tags);
		if (caption == null) {
			return;
		}
		renderCallback.renderPointOfInterestCaption(caption, this.dy, this.fill, this.stroke);
	}

	@Override
	public void renderWay(RenderCallback renderCallback, List<Tag> tags) {
		String caption = this.textKey.getValue(tags);
		if (caption == null) {
			return;
		}
		renderCallback.renderAreaCaption(caption, this.dy, this.fill, this.stroke);
	}

	@Override
	public void scaleStrokeWidth(float scaleFactor) {
		// do nothing
	}

	@Override
	public void scaleTextSize(float scaleFactor) {
		this.fill.setTextSize(this.fontSize * scaleFactor);
		this.stroke.setTextSize(this.fontSize * scaleFactor);
	}

}
