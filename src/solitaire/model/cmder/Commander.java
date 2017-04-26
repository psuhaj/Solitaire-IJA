package solitaire.model.cmder;

import java.util.Stack;

public class Commander {

	//################################################################################################
	public static enum enum_cmd {

		W_T,  // working    => target
		T_W,  // target     => working

		GU_W, // gameDeckUP => working
		W_GU, // working    => gamedeckUP

		GU_T, // gameDeckUP => target
		T_GU, // target     => gameDeckUP

		T_T,  // target     => target

		W_W,  // working    => working

		G_GU, // gameDeck   => gameDeckUP
		GU_G, // gameDeckUP => gameDeck

		GE_GU, // gameDeckEMPTY => gameDeckUP

		Cmd_Null; // null

		@Override
		public String toString() {
			switch(this) {
				case W_T  : return "working    => target       ";
				case T_W  : return "target     => working      ";
				case GU_W : return "gameDeckUP => working      ";
				case W_GU : return "working    => gamedeckUP   ";
				case GU_T : return "gameDeckUP => target       ";
				case T_GU : return "target     => gameDeckUP   ";
				case T_T  : return "target     => target       ";
				case W_W  : return "working    => working      ";
				case G_GU : return "gameDeck   => gameDeckUP   ";
				case GU_G : return "gameDeckUP => gameDeck     ";
				case GE_GU: return "gameDeckEMPTY => gameDeckUP";
				default   : return "Cmd_Null                   ";
			}
		}
	};
	//################################################################################################

	private Stack<enum_cmd> commands_stack;

	public Commander() {
		this.commands_stack = new Stack<enum_cmd>();
	}

	public void cmd_do(Commander.enum_cmd c) {
		this.commands_stack.push(c);
	}

	public Commander.enum_cmd cmd_undo() {
		return this.commands_stack.empty() ? Commander.enum_cmd.Cmd_Null : this.commands_stack.pop();
	}

}
