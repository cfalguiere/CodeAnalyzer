package demo.codeanalyzer;


import java.util.List;

import demo.codeanalyzer.common.AppContext;
import demo.codeanalyzer.rules.AlternateRulesEngine;
import demo.codeanalyzer.rules.ExpressionStatementShouldNotContainsRule;
import demo.codeanalyzer.rules.ShouldNotAssignVariableRule;
import demo.codeanalyzer.rules.TypeShouldNotContainsRule;
import demo.codeanalyzer.rules.VariableNameShouldNotBeRule;
import demo.codeanalyzer.rules.VariableNameShouldNotStartWithRule;
import demo.codeanalyzer.util.FileListLoader;

//TODO csv and console are not sorted the same way
//TODO detect private
//TODO avoid merge when overloading
//TODO detect blocs
//TODO count references
public class Main {

	public static void main(String[] args) {

		String basedir = "/Users/claude.falguiere/Documents/2014-12/CN-ClubMed-SOA/Export-Clubmed";
		String listFilename = "../imports2.txt";
		int max = -1;

		try {

			AppContext context = new AppContext();
			
			FileListLoader loader = new FileListLoader();
			List<String> filenames = loader.loadFilenames(basedir, listFilename, max);
			System.out.format("Selected files %s%n", filenames);
			
			context.createCodeAnalyzer();
			AlternateRulesEngine re = context.createAlternateRulesEngine();
			re.addRule(new TypeShouldNotContainsRule("Statement"));
			re.addRule(new TypeShouldNotContainsRule("Connection"));
			re.addRule(new TypeShouldNotContainsRule("ResultSet"));
			re.addRule(new TypeShouldNotContainsRule("B2CDataManager"));
			re.addRule(new TypeShouldNotContainsRule("TableData"));
			re.addRule(new TypeShouldNotContainsRule("RowData"));
			
			re.addRule(new ExpressionStatementShouldNotContainsRule("checkConnection"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("safeClose"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("safeCommit"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("safeRollback"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("getConnection"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("getConnectionGenesys"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("getPoolConnection"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("prepareCall"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("prepareQuery"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("executeQuery"));
			re.addRule(new ExpressionStatementShouldNotContainsRule("getDataByQuery")); //BV
			re.addRule(new ExpressionStatementShouldNotContainsRule("queryValues")); //BV
			re.addRule(new ExpressionStatementShouldNotContainsRule("executeSql")); //BV

			re.addRule(new VariableNameShouldNotBeRule("sql"));
			re.addRule(new VariableNameShouldNotBeRule("sqlAppel"));
			re.addRule(new VariableNameShouldNotBeRule("requete"));
			re.addRule(new VariableNameShouldNotBeRule("ps"));
			re.addRule(new VariableNameShouldNotBeRule("stmt"));
			re.addRule(new VariableNameShouldNotStartWithRule("PROC_"));
			re.addRule(new VariableNameShouldNotStartWithRule("SELECT_"));
			re.addRule(new VariableNameShouldNotStartWithRule("INSERT_"));
			re.addRule(new VariableNameShouldNotStartWithRule("UPDATE_"));
			re.addRule(new VariableNameShouldNotStartWithRule("DELETE_"));
			re.addRule(new VariableNameShouldNotStartWithRule("SQL_"));

			re.addRule(new ShouldNotAssignVariableRule("sql"));
			re.addRule(new ShouldNotAssignVariableRule("sqlAppel"));
			re.addRule(new ShouldNotAssignVariableRule("requete"));
			re.addRule(new ShouldNotAssignVariableRule("ps"));
			re.addRule(new ShouldNotAssignVariableRule("stmt"));

			CodeAnalyzerApp cac = new CodeAnalyzerApp(context);
			cac.invokeProcessor(filenames);

		} catch (Exception e) {
			System.err.format("Could not handle %s. Reason: %s. Exiting%n", listFilename, e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(255);
		}
	}
	
}
