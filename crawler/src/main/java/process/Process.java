package process;

import domain.Page;

public interface Process {
	public void process(Page page);

	public void processUser(String userUrl);
}
