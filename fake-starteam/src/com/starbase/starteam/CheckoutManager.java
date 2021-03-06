/*****************************************************************************
 * All public interface based on Starteam API are a property of Borland, 
 * those interface are reproduced here only for testing purpose. You should
 * never use those interface to create a competitive product to the Starteam
 * Server. 
 * 
 * The implementation is given AS-IS and should not be considered a reference 
 * to the API. The behavior on a lots of method and class will not be the
 * same as the real API. The reproduction only seek to mimic some basic 
 * operation. You will not found anything here that can be deduced by using
 * the real API.
 * 
 * Fake-Starteam is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *****************************************************************************/
package com.starbase.starteam;

import java.io.IOException;

public class CheckoutManager {

	private View view;
	private CheckoutOptions options;

	public CheckoutManager(View view) {
		this.view = view;
		this.options = new CheckoutOptions(view);
	}

	public CheckoutOptions getOptions() {
		return options;
	}

	public void checkoutTo(File f, java.io.File aFile) {
		if(options.isByTip()) {
			try {
				f.checkoutTo(aFile,
						options.getLockType(),
						true,
						options.getEOLConversionEnabled(),
						options.getUpdateStatus());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(options.isByDate()) {
			try {
				f.checkoutByDate(aFile,
						options.getCheckoutDate(),
						options.getLockType(),
						false,
						options.getEOLConversionEnabled(),
						options.getUpdateStatus());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			throw new UnsupportedOperationException("Cannot checkout as requested " + options);
		}
	}
}
