package com.collect.filelisener;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class FileMonitor {

	private Timer timer;

	private HashMap files_; 

	private Collection listeners; // of WeakReference(FileListener)

	public FileMonitor(long pollingInterval) {
		files_ = new HashMap();
		listeners = new ArrayList();
		timer = new Timer();
		timer.schedule(new FileMonitorNotifier(), 0, pollingInterval);
	}

	public void stop() {
		timer.cancel();
	}

	public void addFile(File file) {
		if (!files_.containsKey(file)) {
			long modifiedTime = file.exists() ? file.lastModified() : -1;
			files_.put(file, new Long(modifiedTime));
		}
	}

	public void removeFile(File file) {
		files_.remove(file);
	}

	public void addListener(FileListener fileListener) {
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			WeakReference reference = (WeakReference) i.next();
			FileListener listener = (FileListener) reference.get();
			if (listener == fileListener)
				return;
		}
		listeners.add(new WeakReference(fileListener));
	}

	public void removeListener(FileListener fileListener) {
		for (Iterator i = listeners.iterator(); i.hasNext();) {
			WeakReference reference = (WeakReference) i.next();
			FileListener listener = (FileListener) reference.get();
			if (listener == fileListener) {
				i.remove();
				break;
			}
		}
	}

	private class FileMonitorNotifier extends TimerTask {
		public void run() {
			Collection files = new ArrayList(files_.keySet());

			for (Iterator i = files.iterator(); i.hasNext();) {
				File file = (File) i.next();
				long lastModifiedTime = ((Long) files_.get(file)).longValue();
				long newModifiedTime = file.exists() ? file.lastModified() : -1;

				if (newModifiedTime != lastModifiedTime) {

					files_.put(file, new Long(newModifiedTime));

					for (Iterator j = listeners.iterator(); j.hasNext();) {
						WeakReference reference = (WeakReference) j.next();
						FileListener listener = (FileListener) reference.get();

						// Remove from list if the back-end object has been GC'd
                        if (listener == null)
                            j.remove();
                        else {
                            listener.fileChanged(file);
                        }
					}
				}
			}
		}
	}


}
