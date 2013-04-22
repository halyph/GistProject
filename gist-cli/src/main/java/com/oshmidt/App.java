package com.oshmidt;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

public class App {
	public static GistManager gistManager = new GistManager();
	public static Logger managerLogger = Logger.getLogger("logfile");

	public static void main(String[] args) {
		managerLogger.info(Messages.getString(
				"com.oshmidt.gistManager.aplicationStartOption",
				StringUtils.convertToString(args)));

		Options options = new Options();

		options.addOption("l", true, "login");
		options.addOption("p", true, "password");
		options.addOption("d", false, "download gists from github");
		options.addOption("show", true, "show loaded gist list");
		options.addOption(
				"download",
				true,
				"download files by gistId or download all gists files if run with \"all\" parameter");
		options.addOption("h", "help", false, "print help to console");

		CommandLineParser parser = new PosixParser();
		CommandLine cmd;
		try {

			cmd = parser.parse(options, args);
			if (cmd.hasOption("l") && cmd.hasOption("p")) {

				gistManager.initUser(cmd.getOptionValue("l"),
						cmd.getOptionValue("p"));
				gistManager.loadAndSaveRemoteGists();
			} else if (cmd.hasOption("d")) {
				gistManager.importUser();
				gistManager.loadAndSaveRemoteGists();
			} else {
				gistManager.readLocalGists();
			}

			if (cmd.hasOption("download")) {
				gistManager.downloadGists(cmd.getOptionValue("download"));
			}

			if (cmd.hasOption("h")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("github gist client",
						"Read following instructions for tuning chat work",
						options, "Developed by oshmidt");
			}

			if (cmd.hasOption("show")) {
				if (cmd.getOptionValue("show").equals("all")) {
					gistManager.showGists();
				}
			}

		} catch (ParseException e) {
			managerLogger.error(e);
		} catch (IOException e) {
			managerLogger.error(e);
		} catch (Exception e) {
			managerLogger.error(e);
		}
	}

}
