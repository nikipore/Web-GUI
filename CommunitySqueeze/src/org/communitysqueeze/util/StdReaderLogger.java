/**
 *  Community Squeeze Configuration Web-GUI
 *
 *  Copyright (C) 2013  Clive Messer <clive.m.messer@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.communitysqueeze.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.apache.log4j.Logger;

/**
 * @author Clive Messer <clive.m.messer@gmail.com>
 *
 */
public class StdReaderLogger extends ShouldRunnable {
	
	private static int THREAD_COUNT = 0;

	private final static Logger LOGGER = Logger.getLogger(StdReaderLogger.class);
	
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private String logPrefix = null;
	
	/**
	 * @param is
	 * @param logPrefix
	 */
	public StdReaderLogger(InputStream is, String logPrefix) {
		
		br = new BufferedReader(new InputStreamReader(is));
		this.logPrefix = logPrefix;
	}

	/**
	 * @param is
	 * @param logPrefix
	 * @param writer
	 */
	public StdReaderLogger(InputStream is, String logPrefix, Writer writer) {
		
		if (writer != null) {
			bw = new BufferedWriter(writer);
		}
		br = new BufferedReader(new InputStreamReader(is));
		this.logPrefix = logPrefix;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("Started");
		}
		
		try {
			String line = null;
			while (shouldRun && (line = br.readLine()) != null) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug(logPrefix + " " + line);
				}
				if (bw != null) {
					bw.write(line + Util.LINE_SEP);
				}
			}
		} catch(IOException ioe) {
			LOGGER.warn("Caught IO Exception!", ioe);
		} finally {
			try {
				br.close();
			} catch (IOException ioe) {}
			
			if (bw != null) {
				try {
					bw.flush();
				} catch (Exception e) {}
				
				try {
					bw.close();
				} catch (Exception e) {}
 			}
		}
		
		if (LOGGER.isTraceEnabled()) {
			LOGGER.trace("Finished");
		}
	}

	/* (non-Javadoc)
	 * @see org.communitysqueeze.util.ShouldRunnable#getNextThreadName(java.lang.String)
	 */
	protected String getNextThreadName(String prefix) {
		
		return prefix + ++THREAD_COUNT;
	}

	/* (non-Javadoc)
	 * @see org.communitysqueeze.util.ShouldRunnable#getNextThreadName()
	 */
	protected String getNextThreadName() {
		
		return getNextThreadName("StdReaderLogger-");
	}		
}
