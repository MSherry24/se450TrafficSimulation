package main;

import view.UI;
import view.UIMenu;
import view.UIMenuAction;
import view.UIMenuBuilder;
import view.UIError;
import view.UIForm;
import view.UIFormTest;
import view.UIFormBuilder;

import java.util.Iterator;
import java.util.Comparator;

import model.PropertyBag;

class Control {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int SUBMENU = 3;
	private static final int NUMSTATES = 10;
	private UIMenu[] _menus;
	private int _state;
	private PropertyBag propertyBag = new PropertyBag();

	private UIFormTest _numberTest;
	private UIFormTest _stringTest;

	private UI _ui;

	Control(UI ui) {
		_ui = ui;

		_menus = new UIMenu[NUMSTATES];
		_state = START;
		addSTART(START);
		addSUBMENU(SUBMENU);
		addEXIT(EXIT);

		UIFormTest yearTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					int i = Integer.parseInt(input);
					return i > 1800 && i < 5000;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		_numberTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					Double.parseDouble(input);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		_stringTest = new UIFormTest() {
			public boolean run(String input) {
				return ! "".equals(input.trim());
			}
		};
	}

	void run() {
		try {
			while (_state != EXITED) {
				_ui.processMenu(_menus[_state]);
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	private void addSTART(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default",
				new UIMenuAction() {
			public void run() {
				_ui.displayError("doh!");
			}
		});
		m.add("Run Simulation",
				new UIMenuAction() {
			//TODO
			public void run() {

			}
		});
		m.add("Change Simulation Parameters",
				new UIMenuAction() {
			public void run() {
				_state = SUBMENU;
			}
		});
		m.add("Exit",
				new UIMenuAction() {
			public void run() {
				_state = EXIT;
			}
		});

		_menus[stateNum] = m.toUIMenu("Main Menu");
	}

	private void addSUBMENU(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default",
				new UIMenuAction() {
			public void run() {
				_ui.displayError("doh!");
			}
		});
		m.add("Show current values",
				new UIMenuAction() {
			public void run() {
				System.out.println(propertyBag.toString());
			}
		});
		m.add("Simulation time step",
				new UIMenuAction() {
			public void run() {
				UIFormBuilder fTimeStep = new UIFormBuilder();
				fTimeStep.add("Time Step", _numberTest);
				
				UIForm _getTimeStepForm = fTimeStep.toUIForm("Enter New Value:");
				String[] result1 = _ui.processForm(_getTimeStepForm);
				Double newTimeStep = Double.parseDouble(result1[0]);

				propertyBag.setTimeStep(newTimeStep);				
			}
		});
		m.add("Simulation run time",
				new UIMenuAction() {
			public void run() {
				//TODO
			}
		});
		m.add("Grid size",
				new UIMenuAction() {
			public void run() {
				//TODO

			}
		});
		m.add("Traffic pattern",
				new UIMenuAction() {
			public void run() {
				//TODO
			}
		});
		m.add("Car entry rate",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Road segment length",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});       
		m.add("Intersection length",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Car length",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Car maximum velocity",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Car stop distance",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Car brake distance",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Traffic light green time",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Traffic light yellow time",
				new UIMenuAction() {
			public void run() {
				// TODO
			}
		});
		m.add("Reset simulation and return to the main menu",
				new UIMenuAction() {
			public void run() {
				_state = START;
			}
		});

		_menus[stateNum] = m.toUIMenu("Settings Menu");

	}
	private void addEXIT(int stateNum) {
		UIMenuBuilder m = new UIMenuBuilder();

		m.add("Default", new UIMenuAction() { public void run() {} });
		m.add("Yes",
				new UIMenuAction() {
			public void run() {
				_state = EXITED;
			}
		});
		m.add("No",
				new UIMenuAction() {
			public void run() {
				_state = START;
			}
		});

		_menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
	}
}