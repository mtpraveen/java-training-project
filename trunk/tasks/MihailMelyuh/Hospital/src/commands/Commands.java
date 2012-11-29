package commands;

public enum Commands {
	MESSAGE {
		@Override
		public ICommand getCommandInstance() {
			return new SendMessageCommand();
		}
	},
	EXIT {
		@Override
		public ICommand getCommandInstance() {
			return new ExitCommand();
		}
	},
	SHUTDOWN {
		@Override
		public ICommand getCommandInstance() {
			return new ShutdownCommand();
		}
	},
	LOADDATA {
		@Override
		public ICommand getCommandInstance() {
			return new LoadDataCommand();
		}
	},
	SAVEDATA {
		@Override
		public ICommand getCommandInstance() {
			return new SaveDataCommand();
		}
	},
	ADDUSER {
		@Override
		public ICommand getCommandInstance() {
			return new AddUserCommand();
		}
	},
	LOGIN {
		@Override
		public ICommand getCommandInstance() {
			return new LoginUserCommand();
		}
	},
	REMOVEUSER {
		@Override
		public ICommand getCommandInstance() {
			return new RemoveUserCommand();
		}
	},
	ADDDATA {
		@Override
		public ICommand getCommandInstance() {
			return new AddDataCommand();
		}
	},
	CHANGEPASSWORD {
		@Override
		public ICommand getCommandInstance() {
			return new ChangePasswordCommand();
		}
	},
	REPORT {
		@Override
		public ICommand getCommandInstance() {
			return new ReportCommand();
		}
	},
	CSVREPORT {
		@Override
		public ICommand getCommandInstance() {
			return new CsvReportCommand();
		}
	},
	SESSIONSTAT {
		@Override
		public ICommand getCommandInstance() {
			return new SessionStatCommand();
		}
	},
	APPOINTDOCTOR {
		@Override
		public ICommand getCommandInstance() {
			return new AppointDoctorCommand();
		}
	},
	APPOINTASSIGNMENT {
		@Override
		public ICommand getCommandInstance() {
			return new AppointAssignmentCommand();
		}
	},
	REMOVEPATIENT {
		@Override
		public ICommand getCommandInstance() {
			return new RemovePatientCommand();
		}
	},
	HELP {
		@Override
		public ICommand getCommandInstance() {
			return new HelpCommand();
		}
	};
	public abstract ICommand getCommandInstance();
}
