package dev.theancientowl.feathershowcase.modules.dummy.components;

import dev.defaultybuf.feather.toolkit.api.FeatherModule;
import dev.theancientowl.feathershowcase.modules.dummy.interfaces.IDummy;

/**
 * @brief Module responsible for issuing teleports
 *        when the player right clicks a red wool block :)
 * @extends FeatherModule - in order to be managed by the toolkit
 * @implements IDummy - module interface
 */
public class DummyModule extends FeatherModule implements IDummy {

    public DummyModule(InitData data) {
        super(data);
    }

}
