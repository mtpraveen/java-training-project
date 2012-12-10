package epam.course.commands;

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
	SHOWCARS {

		@Override
		public ICommand getCommandInstance() {
			return new ShowCarsCommand();
		}
		
	},
	SHOWORDERS {

		@Override
		public ICommand getCommandInstance() {
			return new ShowOrdersCommand();
		}
		
	},
	CHANGELANGUAGE {

		@Override
		public ICommand getCommandInstance() {
			return new ChangeLanguageCommand();
		}
		
	},
	ORDER {

		@Override
		public ICommand getCommandInstance() {
			return new OrderCommand();
		}
		
	},
	REGISTRATION {

		@Override
		public ICommand getCommandInstance() {
			return new RegistrationCommand();
		}
		
	},
	LOGIN {

		@Override
		public ICommand getCommandInstance() {
			return new LoginCommand();

		}

	};
	public abstract ICommand getCommandInstance();
}
