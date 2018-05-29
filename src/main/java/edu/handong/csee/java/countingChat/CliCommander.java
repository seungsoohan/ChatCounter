package edu.handong.csee.java.countingChat;

	import org.apache.commons.cli.CommandLine;
	import org.apache.commons.cli.CommandLineParser;
	import org.apache.commons.cli.DefaultParser;
	import org.apache.commons.cli.HelpFormatter;
	import org.apache.commons.cli.Option;
	import org.apache.commons.cli.Options;
	/**
	 */

	public class CliCommander {
		/**
		 *
		 */
		String inputPath;
		/**
		 * 
		 */
		String outputPath;
		/**
		 * 
		 */
		boolean help;

		/**
		 * 
		 */
		public void run(String[] args) {
			Options options = createOptions();

			if(parseOptions(options, args)){
				if (help){
					printHelp(options);
					return;
				}
			}
		}

		private boolean parseOptions(Options options, String[] args) {
			CommandLineParser parser = new DefaultParser();

			try {

				CommandLine cmd = parser.parse(options, args);

				inputPath = cmd.getOptionValue("i");
				outputPath = cmd.getOptionValue("o");
				help = cmd.hasOption("h");

			} catch (Exception e) {
				printHelp(options);
				return false;
			}

			return true;
		}

		private Options createOptions() {
			Options options = new Options();

			options.addOption(Option.builder("i").longOpt("input")
					.desc("Set a path of a directory that input data")
					.hasArg()
					.argName("input option")
					.required()
					.build());

			options.addOption(Option.builder("o").longOpt("output")
					.desc("Set a path of a directory that ouput data")
					.hasArg()
					.argName("output option")
					.required()
					.build());

			options.addOption(Option.builder("h").longOpt("help")
					.desc("Help")
					.build());

			return options;
		}

		private void printHelp(Options options) {
			HelpFormatter formatter = new HelpFormatter();
			String header = "CLI program";
			String footer ="\nThis program is count how many time chating on kakao";
			formatter.printHelp("CLIExample", header, options, footer, true);
		}
	}

