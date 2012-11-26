package com.epam.library.commands;

public enum Commands {
	MESSAGE {
		@Override
		public ICommand getCommandInstance() {
			return new SendMessageCommand();
		}
	},
	ADDUSER {
		@Override
		public ICommand getCommandInstance() {
			return new AddUserCommand();
		}
	},
	DELLETEUSER {
		public ICommand getCommandInstance() {
			return new DelleteUserCommand();
		}
	},
	LOADTHEDATA {
		public ICommand getCommandInstance() {
			return new LoadDataCommand();
		}
	},
	GETSTATISTIC {
		public ICommand getCommandInstance() {
			return new GetStatisticCommand();
		}
	},
	SHOUTDOWN {
		public ICommand getCommandInstance() {
			return new ShoutDownCommand();
		}
	},
	LOGIN {
		public ICommand getCommandInstance() {
			return new LoginCommand();
		}
	},
	CHANGETHELANGUAGE {
		public ICommand getCommandInstance() {
			return new ChangeTheLanguageCommand();
		}
	},
	CHANGETHEPASSWORD {
		public ICommand getCommandInstance() {
			return new ChangeThePasswordCommand();
		}
	},
	ENDTHESESSION {
		public ICommand getCommandInstance() {
			return new EndTheSessionCommand();
		}
	},
	GETREPORT {
		public ICommand getCommandInstance() {
			return new GetReportCommand();
		}
	},
	GETREPORTINFILE {
		public ICommand getCommandInstance() {
			return new GetReportInFile();
		}
	},
	WRITEINBLACKLIST {
		public ICommand getCommandInstance() {
			return new WriteInBlackListCommand();
		}
	},
	ORDERTHEBOOK {
		public ICommand getCommandInstance() {
			return new OrderTheBookCommand();
		}
	};

	public abstract ICommand getCommandInstance();
}
