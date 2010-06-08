package org.basex.examples.create;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.proc.Create;
import org.basex.core.proc.DropDB;
import org.basex.core.proc.XQuery;

/**
 * This example demonstrates how to import a filesystem structure
 * into the database.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-10, ISC License
 * @author Christian Gruen
 */
public final class SimpleFSExample {
  /** Private constructor. */
  private SimpleFSExample() { }

  /**
   * Main test method.
   * @param args command-line arguments
   * @throws BaseXException if a database command fails
   */
  public static void main(final String[] args) throws BaseXException {

    System.out.println("=== SimpleFSExample ===");

    // ------------------------------------------------------------------------
    // Create database context
    final Context context = new Context();

    // input path and name of database
    final String path = ".";
    final String name = "fsexample";

    // ------------------------------------------------------------------------
    // Import a directory as database
    System.out.println("\n* Import a directory.");

    new Create(new SimpleFSParser(path), name).execute(context);

    // ------------------------------------------------------------------------
    // Perform query
    System.out.println("\n* Number of created elements:");

    new XQuery("count(//*)").execute(context, System.out);

    // ------------------------------------------------------------------------
    // Drop database and close context
    System.out.println("\n\n* Drop database.");

    new DropDB(name).execute(context);

    context.close();
  }
}