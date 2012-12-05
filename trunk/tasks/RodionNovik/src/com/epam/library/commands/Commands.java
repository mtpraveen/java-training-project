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
	SHUTDOWN {
		public ICommand getCommandInstance() {
			return new ShutDownCommand();
		}
	},
	LOGIN {
		public ICommand getCommandInstance() {
			return new LoginCommand();
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
	RETURNTHEBOOK {
		public ICommand getCommandInstance(){
			return new ReturnTheBookCommand();
		}
	},
	ORDERTHEBOOK {
		public ICommand getCommandInstance() {
			return new OrderTheBookCommand();
		}
	};

	public abstract ICommand getCommandInstance();
}
